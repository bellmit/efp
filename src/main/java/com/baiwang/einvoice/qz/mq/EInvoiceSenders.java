package com.baiwang.einvoice.qz.mq;

import javax.annotation.Resource;
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;  
import org.springframework.stereotype.Service;

@Service
public class EInvoiceSenders {
	
	@Autowired
	private ActiveMQQueue einvoiceMQ;

	@Autowired
    private JmsTemplate jmsTemplate;
	
	public void sendMessageOfKjfp(final String message) {
		
		sendMessage(einvoiceMQ, message);
		
	}
	
	public void sendMessage(Destination destination, final String message) {
        
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
	
}
