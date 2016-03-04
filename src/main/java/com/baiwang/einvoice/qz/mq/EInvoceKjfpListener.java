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

import com.baiwang.einvoice.service.skService.SkService;
import com.baiwang.einvoice.util.InvoiceUtil;

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
	
	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage)message;  
		String xml = msg.getText();
		
		Message textMessage = null;
		
		String returnSK = skService.reqestSK(xml);
		
		logger.info("***********税控服务器返回****************" + returnSK);
		logger.info("***********转换ubl****************returnCode:" + InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>"));
		if(returnSK == null || !InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>").equals("0000") ){//税控失败
	        textMessage = session.createTextMessage(returnSK);
	        logger.info("******请求sk失败**********");
	        
	        textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
			jmsTemplate2.convertAndSend(textMessage);
		}else{
			if("此发票已经开具过".equals( InvoiceUtil.getIntervalValue(returnSK, "<RETURNMSG>", "</RETURNMSG>"))){
				logger.info("///////-----￥----发票请求流水号：////////" + InvoiceUtil.getIntervalValue(returnSK, "<FPQQLSH>", "</FPQQLSH>") + ",此发票已经开具过。");
				textMessage = session.createTextMessage(InvoiceUtil.backMsg("4007", "此发票已经开具过。", xml));
				textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
				jmsTemplate2.convertAndSend(textMessage);
			}else{
				Map<String, String> map = new HashMap<String, String>();
				map.put("xml", xml);
				map.put("returnSK", returnSK);
				tsSender.sendMessage(map);
			}
		} 
		
		
		
	}
	
	

}
