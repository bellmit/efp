package com.baiwang.einvoice.qz.controller;

import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.baiwang.einvoice.qz.service.IResultOfSkService;
import com.baiwang.einvoice.qz.utils.InvoiceUtil;

public class EnumResposeMessageTask implements  Callable<String>{
	
	private static final Log logger = LogFactory.getLog(EnumResposeMessageTask.class);
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	private String ddhm;
	
	private String correlationId;
	
	@Resource
	private IResultOfSkService resultService;
	
	public EnumResposeMessageTask(String ddhm, String correlationId, JmsTemplate jmsTemplate2, IResultOfSkService resultService){
		this.ddhm =  ddhm;
		this.correlationId = correlationId;
		this.jmsTemplate2 = jmsTemplate2;
		this.resultService = resultService;
	}
	
	public String call(){
		String text = "";
		try {
			TextMessage receive = (TextMessage) jmsTemplate2.receiveSelected("JMSCorrelationID = '" + correlationId +"'");
			text = receive.getText();
			logger.info("*****JMSCorrelationID为:" + ddhm + "接收到的message为：" + text);
		} catch (Exception e1) {
			logger.info("**eee***JMSCorrelationID为:" + ddhm + "接收到的message为：" + text);
			System.out.println(" exception======"+e1.getMessage());
			e1.printStackTrace();
		}
		
		if(InvoiceUtil.getIntervalValue(text,"<RETURNCODE>","</RETURNCODE>").equals("0000")){
			return "0000";
		}else{
			return InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>").equals("")?
					InvoiceUtil.getIntervalValue(text,"<returnmsg>","</returnmsg>"):
						InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>");
		}
		
    }
	
	
}
