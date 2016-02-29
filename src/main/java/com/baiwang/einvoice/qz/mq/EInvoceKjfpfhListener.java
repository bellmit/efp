package com.baiwang.einvoice.qz.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("rawtypes")
public class EInvoceKjfpfhListener implements SessionAwareMessageListener{
	
	private Log logger = LogFactory.getLog(EInvoceKjfpfhListener.class);

	private String returnMsg = "";
	
	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage)message;  
		returnMsg = msg.getText();
		logger.info("///////////////////ts/sk////////////返回：" + returnMsg);
		
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	
	
	

}
