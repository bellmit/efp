package com.baiwang.einvoice.qz.mq.dzfp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.OrderDetail;
import com.baiwang.einvoice.qz.mq.RequestTsSender;
import com.baiwang.einvoice.qz.service.FpService;
import com.baiwang.einvoice.qz.service.IResultOfSkService;
import com.baiwang.einvoice.qz.utils.JAXBUtil;
import com.baiwang.einvoice.qz.utils.ValidateXML;
import com.baiwang.einvoice.qz.utils.XmlUtil;
import com.baiwang.einvoice.service.skService.SkService;
import com.baiwang.einvoice.util.InvoiceUtil;

@SuppressWarnings("rawtypes")
public class DzfpHandleQueneListener implements SessionAwareMessageListener {

	private static final Log logger = LogFactory.getLog(DzfpHandleQueneListener.class);
	
	@Resource
    private JmsTemplate jmsTemplate2;//响应队列
	
	@Resource
	private IResultOfSkService resultService;
	
	@Resource
	private FpService fpService;
	
	@Resource
	private SkService skService;
	
	@Resource
	private RequestTsSender tsSender;
	
	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage)message;  
		String xml = msg.getText();
		String fpqqlsh = msg.getJMSCorrelationID();
		
		Message textMessage = null;
		
		try {
			if( !ValidateXML.validateXml("wyyy.xsd", xml.getBytes("gbk")) ){//不符合规则，直接返回到请求返回队列
				logger.error("发票请求流水号：" + fpqqlsh + "xml不符合规则");
				logger.info("发送响应队列的报文：" + InvoiceUtil.backMsg("400", "xml不符合规则", xml));
				textMessage = session.createTextMessage(InvoiceUtil.backMsg("400", "xml不符合规则", xml));
				textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
				jmsTemplate2.convertAndSend(textMessage);
			}else{
				Business business = JAXBUtil.unmarshallObject(xml.getBytes("gbk"));
				OrderDetail orderDetail = business.getOrderDetail();
				Kpxx kpxx = business.getREQUESTCOMMONFPKJ().getKpxx();
				
				List<Fpmx> list = business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx();
				System.out.println("订单号："+orderDetail.getZddh());
				
				Map<String, String> result = resultService.queryResult(kpxx.getZddh(), kpxx.getFddh(), kpxx.getFplx());//根据两个订单号查
				
				if(null == result || result.get("returnCode").equals("4000")){
					fpService.saveInfo(orderDetail, kpxx, list , fpqqlsh);
					
					//Message textMessage = null;
					String returnSK = "";
					xml = XmlUtil.toEInvoice(kpxx,list).toString();
					logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX请求税控服务器的xml：" + xml);
					try {
						logger.info("请求税控服务器的xml：" + XmlUtil.toEInvoice(kpxx,list).toString());
						returnSK = skService.reqestSK(xml);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Map<String, String> resultMap = new HashMap<>();
					String returnCode = InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>").equals("")? 
							InvoiceUtil.getIntervalValue(returnSK,"<returncode>","</returncode>"):
								InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>");
					String returnMsg = InvoiceUtil.getIntervalValue(returnSK,"<RETURNMSG>","</RETURNMSG>").equals("")?
							InvoiceUtil.getIntervalValue(returnSK,"<returnmsg>","</returnmsg>"):
								InvoiceUtil.getIntervalValue(returnSK,"<RETURNMSG>","</RETURNMSG>");
					String fpzt = "-1";		
					
					System.out.println("税控服务器返回："+ returnSK);
					logger.info("***********税控服务器返回****************" + returnSK);
					logger.info("***********税控服务器返回****************returnCode:" + InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>"));
					if(returnSK == null || !InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>").equals("0000") ){//税控失败
				        textMessage = session.createTextMessage(returnSK);
				        logger.info("******请求sk失败**********");
				        
					}else{
						fpzt = "3";
						if("此发票已经开具过".equals( InvoiceUtil.getIntervalValue(returnSK, "<RETURNMSG>", "</RETURNMSG>"))){
							logger.info("///////-----￥----发票请求流水号：////////" + InvoiceUtil.getIntervalValue(returnSK, "<FPQQLSH>", "</FPQQLSH>") + ",此发票已经开具过。");
							textMessage = session.createTextMessage(InvoiceUtil.backMsg("400", "此发票已经开具过。", xml));
						}else{
							textMessage = session.createTextMessage(returnSK);
							Map<String, String> map = new HashMap<String, String>();
							map.put("xml", xml);
							map.put("returnSK", returnSK);
							tsSender.sendMessage(map);
						}
						resultMap.put("jqbh", InvoiceUtil.getIntervalValue(returnSK, "<JQBH>", "</JQBH>"));
						resultMap.put("fpdm", InvoiceUtil.getIntervalValue(returnSK, "<FP_DM>", "</FP_DM>"));
						resultMap.put("fphm", InvoiceUtil.getIntervalValue(returnSK, "<FP_HM>", "</FP_HM>"));
						resultMap.put("kprq", InvoiceUtil.getIntervalValue(returnSK, "<KPRQ>", "</KPRQ>"));
						resultMap.put("skm", InvoiceUtil.getIntervalValue(returnSK, "<FP_MW>", "</FP_MW>"));
						resultMap.put("jym", InvoiceUtil.getIntervalValue(returnSK, "<JYM>", "</JYM>"));
						resultMap.put("ewm", InvoiceUtil.getIntervalValue(returnSK, "<EWM>", "</EWM>"));
						resultMap.put("bz", InvoiceUtil.getIntervalValue(returnSK, "<BZ>", "</BZ>"));
					} 
					textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
					jmsTemplate2.convertAndSend(textMessage);
					
					resultMap.put("fpqqlsh", fpqqlsh);
					resultMap.put("returnCode", returnCode);
					resultMap.put("returnMsg", returnMsg);
					resultMap.put("fpzt", fpzt);
					
					resultService.saveResultOfSk(resultMap);
					
				}else if("0000".equals(result.get("returnCode"))){
					logger.warn("*********订单号：" + orderDetail.getZddh()+"/"+orderDetail.getFddh() + "已经开票成功，返回。");
					textMessage = session.createTextMessage(InvoiceUtil.backMsg("0000", "发票已开具成功", xml));
					textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
					jmsTemplate2.convertAndSend(textMessage);
				}else{
					logger.warn("*********订单号：" + orderDetail.getZddh()+"/"+orderDetail.getFddh() + ",returnCode:" + 
							result.get("returnCode") + ",returnMsg:" + result.get("returnMsg"));
					textMessage = session.createTextMessage(InvoiceUtil.backMsg(result.get("returnCode"), result.get("returnMsg"), xml));
					textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
					jmsTemplate2.convertAndSend(textMessage);
				}
			}
		} catch (JmsException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
