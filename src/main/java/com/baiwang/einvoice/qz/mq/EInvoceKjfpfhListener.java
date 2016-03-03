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
public class EInvoceKjfpfhListener implements SessionAwareMessageListener {

	private Log logger = LogFactory.getLog(EInvoceKjfpfhListener.class);

	private static Map<String, String> map = new HashMap<String, String>();

	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage) message;

		logger.info("************开票结果*****correlationId:"
				+ msg.getJMSCorrelationID() + "****返回：" + msg.getText());

		synchronized (this) {
			map.put(msg.getJMSCorrelationID(), msg.getText());
		}
	}

	public static Map<String, String> getMap() {
		return map;
	}

}
