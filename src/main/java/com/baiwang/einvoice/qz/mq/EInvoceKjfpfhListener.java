package com.baiwang.einvoice.qz.mq;

import java.util.HashMap;
import java.util.Map;

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
	private static Map<String, String> map = new HashMap<String, String>();
	
	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage)message;  
		returnMsg = msg.getText();
		logger.info("///////////////////ts/sk////////////返回：" + returnMsg);
		System.out.println("**************msg.getJMSCorrelationID()**************"+ msg.getJMSCorrelationID());
		System.out.println("第2次接收到mes id：" + msg.getJMSMessageID());
		
		map.put(msg.getJMSCorrelationID(),returnMsg);
		System.out.println("map map  map ----第2次接收到mes id：" + map.get(msg.getJMSCorrelationID()));
		
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public static Map<String, String> getMap() {
		return map;
	}

	public static void setMap(Map<String, String> map) {
		EInvoceKjfpfhListener.map = map;
	}
	
	
	
	
	
	

}
