package com.baiwang.einvoice.qz.controller;

import java.util.concurrent.Callable;

import javax.annotation.Resource;
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
	
	private String correlationId;
	
	@Resource
	private IResultOfKpService resultService;
	
	public EnumResposeMessageTask(String ddhm, String correlationId, JmsTemplate jmsTemplate2, IResultOfKpService resultService){
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
		
		System.out.println("))))))))))sk  call   _____---" + text);
		ResultOfKp result = new ResultOfKp();
		result.setDdhm(ddhm);
		result.setCorrelationid(correlationId);
		result.setCode(InvoiceUtil.getIntervalValue(text,"<RETURNCODE>","</RETURNCODE>").equals("")? 
				InvoiceUtil.getIntervalValue(text,"<returncode>","</returncode>"):
					InvoiceUtil.getIntervalValue(text,"<RETURNCODE>","</RETURNCODE>"));
		result.setMsg(InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>").equals("")?
				InvoiceUtil.getIntervalValue(text,"<returnmsg>","</returnmsg>"):
					InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>"));
		result.setFpqqlsh(InvoiceUtil.getIntervalValue(text,"<FPQQLSH>","</FPQQLSH>"));
		try{
			int suc = resultService.save(result);
		}catch(Exception e){
			logger.error("*****订单号码：" + ddhm + "保存结果出现异常，code:"+InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>")
			+ ",msg:" + InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>") + ",fpqqlsh:" +
			InvoiceUtil.getIntervalValue(text,"<FPQQLSH>","</FPQQLSH>"));
			e.printStackTrace();
		}
		
		
        return text;
    }
	
	
}
