package com.baiwang.einvoice.qz.mq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.service.impl.ResultOfSkServiceImpl;
import com.baiwang.einvoice.qz.service.skService.SkService;
import com.baiwang.einvoice.qz.utils.InvoiceUtil;

@Service
@SuppressWarnings("rawtypes")
public class EInvoceKjfpListener implements SessionAwareMessageListener{
	
	private Log logger = LogFactory.getLog(EInvoceKjfpListener.class);

	@Resource
	private Destination einvoiceKjfpfhMQ;
	
	@Resource
	private SkService skService;

	@Resource
	private RequestTsSender tsSender;
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	@Resource
	private ResultOfSkServiceImpl resultService;
	
	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage)message;  
		String xml = msg.getText();
		String fpqqlsh = msg.getJMSCorrelationID();
		
		Message textMessage = null;
		System.out.println("请求税控服务器的xml：" + xml);
		String returnSK = skService.reqestSK(xml);
		
		Map<String, String> result = new HashMap<>();
		String returnCode = InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>").equals("")? 
				InvoiceUtil.getIntervalValue(returnSK,"<returncode>","</returncode>"):
					InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>");
		String returnMsg = InvoiceUtil.getIntervalValue(returnSK,"<RETURNMSG>","</RETURNMSG>").equals("")?
				InvoiceUtil.getIntervalValue(returnSK,"<returnmsg>","</returnmsg>"):
					InvoiceUtil.getIntervalValue(returnSK,"<RETURNMSG>","</RETURNMSG>");
		String fpzt = "-1";		
		
		System.out.println("税控服务器返回："+ returnSK);
		logger.info("***********税控服务器返回****************" + returnSK);
		logger.info("***********转换ubl****************returnCode:" + InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>"));
		if(returnSK == null || !InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>").equals("0000") ){//税控失败
	        textMessage = session.createTextMessage(returnSK);
	        logger.info("******请求sk失败**********");
	        
		}else{
			fpzt = "3";
			if("此发票已经开具过".equals( InvoiceUtil.getIntervalValue(returnSK, "<RETURNMSG>", "</RETURNMSG>"))){
				logger.info("///////-----￥----发票请求流水号：////////" + InvoiceUtil.getIntervalValue(returnSK, "<FPQQLSH>", "</FPQQLSH>") + ",此发票已经开具过。");
				textMessage = session.createTextMessage(InvoiceUtil.backMsg("4007", "此发票已经开具过。", xml));
			}else{
				textMessage = session.createTextMessage(returnSK);
				Map<String, String> map = new HashMap<String, String>();
				map.put("xml", xml);
				map.put("returnSK", returnSK);
				tsSender.sendMessage(map);
			}
			result.put("jqbh", InvoiceUtil.getIntervalValue(returnSK, "<JQBH>", "</JQBH>"));
			result.put("fpdm", InvoiceUtil.getIntervalValue(returnSK, "<FP_DM>", "</FP_DM>"));
			result.put("fphm", InvoiceUtil.getIntervalValue(returnSK, "<FP_HM>", "</FP_HM>"));
			result.put("kprq", InvoiceUtil.getIntervalValue(returnSK, "<KPRQ>", "</KPRQ>"));
			result.put("skm", InvoiceUtil.getIntervalValue(returnSK, "<FP_MW>", "</FP_MW>"));
			result.put("jym", InvoiceUtil.getIntervalValue(returnSK, "<JYM>", "</JYM>"));
			result.put("ewm", InvoiceUtil.getIntervalValue(returnSK, "<EWM>", "</EWM>"));
			result.put("bz", InvoiceUtil.getIntervalValue(returnSK, "<BZ>", "</BZ>"));
		} 
		textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
		jmsTemplate2.convertAndSend(textMessage);
		
		result.put("fpqqlsh", fpqqlsh);
		result.put("returnCode", returnCode);
		result.put("returnMsg", returnMsg);
		result.put("fpzt", fpzt);
		
		resultService.saveResultOfSk(result);
		
	}
	
	

}
