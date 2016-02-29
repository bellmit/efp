package com.baiwang.einvoice.qz.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class EInvoceReceivers implements SessionAwareMessageListener{

	
	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage)message;  
        
		
		System.out.println(msg.getText());
		
		
	}

}
