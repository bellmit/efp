package com.baiwang.einvoice.qz.controller;

import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.baiwang.einvoice.qz.beans.ResultOfKp;
import com.baiwang.einvoice.qz.service.IResultOfKpService;
import com.baiwang.einvoice.util.InvoiceUtil;

public class EnumResposeMessageTask implements  Callable<String>{
	
	private static final Log logger = LogFactory.getLog(EnumResposeMessageTask.class);
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	private String ddhm;
	
	@Resource
	private IResultOfKpService resultService;
	
	public EnumResposeMessageTask(String ddhm){
		this.ddhm =  "JMSCorrelationID = '" + ddhm +"'";
		System.out.println("-------------ddhm:"+ddhm);
	}
	
	public String call(){
		System.out.println("*******ddhm-----" + ddhm);
		String text = "";
		try {
			TextMessage receive = (TextMessage) jmsTemplate2.receiveSelected(ddhm);
			text = receive.getText();
			logger.info("*****JMSCorrelationID为:" + ddhm + "接收到的message为：" + text);
		} catch (JMSException e1) {
			System.out.println(" exception======"+e1.getMessage());
			e1.printStackTrace();
		}
		
		ResultOfKp result = new ResultOfKp();
		result.setCode(InvoiceUtil.getIntervalValue(text,"<RETURNCODE>","</RETURNCODE>"));
		result.setMsg(InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>"));
		result.setFpqqlsh(InvoiceUtil.getIntervalValue(text,"<FPQQLSH>","</FPQQLSH>"));
		try{
			resultService.save(result);
		}catch(Exception e){
			logger.error("*****订单号码：" + ddhm + "保存结果出现异常，code:"+InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>")
			+ ",msg:" + InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>") + ",fpqqlsh:" +
			InvoiceUtil.getIntervalValue(text,"<FPQQLSH>","</FPQQLSH>"));
		}
		
		
        return text;
    }
	
	
}
