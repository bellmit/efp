package com.baiwang.einvoice.qz.mq.zzfp;

import java.io.UnsupportedEncodingException;
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
import com.baiwang.einvoice.qz.service.FpService;
import com.baiwang.einvoice.qz.service.IResultOfSkService;
import com.baiwang.einvoice.qz.utils.JAXBUtil;
import com.baiwang.einvoice.qz.utils.ValidateXML;
import com.baiwang.einvoice.util.InvoiceUtil;

@SuppressWarnings("rawtypes")
public class ZzfpHandleQueneListener implements SessionAwareMessageListener{

	private static final Log logger = LogFactory.getLog(ZzfpHandleQueneListener.class);
	
	@Resource
    private JmsTemplate jmsTemplate2;//响应队列
	
	@Resource
	private IResultOfSkService resultService;
	
	@Resource
	private FpService fpService;
	
	@Override
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
					
					logger.warn("*********订单号：" + orderDetail.getZddh()+"/"+orderDetail.getFddh() + "纸质发票保存成功");
					textMessage = session.createTextMessage(InvoiceUtil.backMsg("8000", "订单保存成功", xml));
					textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
					jmsTemplate2.convertAndSend(textMessage);
					
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
