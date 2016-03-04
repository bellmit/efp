package com.baiwang.einvoice.qz.mq;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class RequestTsSender {
	
	private Log logger = LogFactory.getLog(RequestTsSender.class);

	@Autowired
    private JmsTemplate jmsTemplate3;
	
	@Resource
	private Destination requestTsQuery;
	
	public void sendMessage(final Map<String, String> map) {
        logger.info("请求发票平台 xml：" + map.get("xml") + ",税控返回："+ map.get("returnSK"));
		jmsTemplate3.send(requestTsQuery, new MessageCreator() {
        	
            public Message createMessage(Session session) throws JMSException {
            	MapMessage mapMessage = session.createMapMessage();
            	mapMessage.setString("xml", map.get("xml"));
            	mapMessage.setString("returnSK", map.get("returnSK"));
            	
//            	textMessage.setJMSReplyTo(requestTsQuery);
//            	textMessage.setJMSCorrelationID(correlationId);
            	
                return mapMessage;
            }
        });
        
    }
	
}
