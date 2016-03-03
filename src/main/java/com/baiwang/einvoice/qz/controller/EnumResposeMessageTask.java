package com.baiwang.einvoice.qz.controller;

import java.util.concurrent.Callable;

import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class EnumResposeMessageTask implements  Callable<String>{
	
	private static final Log logger = LogFactory.getLog(EnumResposeMessageTask.class);
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	private String messageSelector;
	
	public EnumResposeMessageTask(String xx){
		this.messageSelector = "JMSCorrelationID = '" + xx +"'";
	}
	
	public String call() throws Exception {
		
		TextMessage receive = (TextMessage) jmsTemplate2.receiveSelected(messageSelector);
		logger.info("*****JMSCorrelationID为:" + messageSelector + "接收到的message为：" + receive.getText());
		
		
		
        return receive.getText();
    }
	
	
}
