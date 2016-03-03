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
	
	@Resource
	private IResultOfKpService resultService;
	
	public EnumResposeMessageTask(String xx){
		this.ddhm =  xx;
	}
	
	public String call() throws Exception {
		
		TextMessage receive = (TextMessage) jmsTemplate2.receiveSelected("JMSCorrelationID = '" + ddhm +"'");
		logger.info("*****JMSCorrelationID为:" + ddhm + "接收到的message为：" + receive.getText());
		
		ResultOfKp result = new ResultOfKp();
		result.setCode(InvoiceUtil.getIntervalValue(receive.getText(),"<RETURNCODE>","</RETURNCODE>"));
		result.setMsg(InvoiceUtil.getIntervalValue(receive.getText(),"<RETURNMSG>","</RETURNMSG>"));
		result.setFpqqlsh(InvoiceUtil.getIntervalValue(receive.getText(),"<FPQQLSH>","</FPQQLSH>"));
		try{
			resultService.save(result);
		}catch(Exception e){
			logger.error("*****订单号码：" + ddhm + "保存结果出现异常，code:"+InvoiceUtil.getIntervalValue(receive.getText(),"<RETURNMSG>","</RETURNMSG>")
			+ ",msg:" + InvoiceUtil.getIntervalValue(receive.getText(),"<RETURNMSG>","</RETURNMSG>") + ",fpqqlsh:" +
			InvoiceUtil.getIntervalValue(receive.getText(),"<FPQQLSH>","</FPQQLSH>"));
		}
		
		
        return receive.getText();
    }
	
	
}
