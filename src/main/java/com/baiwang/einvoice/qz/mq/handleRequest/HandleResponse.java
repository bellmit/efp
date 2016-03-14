package com.baiwang.einvoice.qz.mq.handleRequest;

import java.util.concurrent.Callable;

import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.baiwang.einvoice.qz.vo.Returndata;
import com.baiwang.einvoice.qz.vo.Returnmsg;
import com.baiwang.einvoice.util.InvoiceUtil;

public class HandleResponse implements  Callable<Returnmsg> {
	
	private static final Log logger = LogFactory.getLog(HandleResponse.class);
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	private String fpqqlsh;
	
	public HandleResponse(String fpqqlsh, JmsTemplate jmsTemplate2) {
		super();
		this.jmsTemplate2 = jmsTemplate2;
		this.fpqqlsh = fpqqlsh;
	}
	public HandleResponse(){
		super();
	}

	public Returnmsg call(){
		Returnmsg returnmsg = new Returnmsg();
		String text = "";
		try {
			TextMessage receive = (TextMessage) jmsTemplate2.receiveSelected("JMSCorrelationID = '" + fpqqlsh +"'");
			text = receive.getText();
			
			logger.info("*****JMSCorrelationID为:" + fpqqlsh + "接收到的message为：" + text);
		} catch (Exception e1) {
			logger.error("**Exception***JMSCorrelationID为:" + fpqqlsh + "接收到的message为：" + text);
			e1.printStackTrace();
		}
		
		if(InvoiceUtil.getIntervalValue(text,"<RETURNCODE>","</RETURNCODE>").equals("0000")){
			Returndata returndata = new Returndata();
			returndata.setFpdm(InvoiceUtil.getIntervalValue(text,"<FP_DM>","</FP_DM>"));
			returndata.setFphm(InvoiceUtil.getIntervalValue(text,"<FP_HM>","</FP_HM>"));
			returndata.setKprq(InvoiceUtil.getIntervalValue(text,"<KPRQ>","</KPRQ>"));
			return returnmsg.setReturncode("0").setReturnmsg("开票成功").setReturndata(returndata);
		}else if(InvoiceUtil.getIntervalValue(text,"<RETURNCODE>","</RETURNCODE>").equals("8000")){
			return returnmsg.setReturncode("0").setReturnmsg("订单保存成功");
		}else{
			 String msg = InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>").equals("")?
					InvoiceUtil.getIntervalValue(text,"<returnmsg>","</returnmsg>"):
						InvoiceUtil.getIntervalValue(text,"<RETURNMSG>","</RETURNMSG>");
					
			return returnmsg.setReturncode("400").setReturnmsg(msg);
		}
		
    }
}
