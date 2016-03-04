package com.baiwang.einvoice.qz.mq;

import javax.annotation.Resource;
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;  
import org.springframework.stereotype.Service;

@Service
public class EInvoiceSenders {
	
	@Autowired
	private ActiveMQQueue einvoiceMQ;
	
	@Resource
	private Destination einvoiceKjfpfhMQ;

	@Autowired
    private JmsTemplate jmsTemplate1;
	
	public void sendMessage(final String message) {
		
		sendMessage(einvoiceMQ, message, null);
		
	}
	
	public void sendMessage(Destination destination, final String message) {
		
		sendMessage(einvoiceMQ, message, null);
		
	}
	
	public void sendMessage(String message, String correlationId) {
		
		sendMessage(einvoiceMQ, message, correlationId);
		
	}
	
	public void sendMessage(Destination destination, final String message, final String correlationId) {
        
		jmsTemplate1.send(destination, new MessageCreator() {
        	
            public Message createMessage(Session session) throws JMSException {
            	TextMessage textMessage = session.createTextMessage(message);
            	
            	textMessage.setJMSReplyTo(einvoiceKjfpfhMQ);
            	textMessage.setJMSCorrelationID(correlationId);
            	
                return textMessage;
            }
        });
        
    }
	
}
