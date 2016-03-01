package com.baiwang.einvoice.qz.mq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.service.skService.SkService;
import com.baiwang.einvoice.service.skService.TsPlatService;
import com.baiwang.einvoice.util.InvoiceUtil;

@Service
@SuppressWarnings("rawtypes")
public class EInvoceKjfpListener implements SessionAwareMessageListener{
	
	private Log logger = LogFactory.getLog(EInvoceKjfpListener.class);

	@Resource
	private Destination einvoiceKjfpfhMQ;
	
	@Resource
	private SkService skService;

	@Resource
	private TsPlatService tsService;
	
	@SuppressWarnings("static-access")
	public void onMessage(Message message, Session session) throws JMSException {
		TextMessage msg = (TextMessage)message;  
		String xml = msg.getText();
		
		String returnSK = skService.reqestSK(xml);
		String ubl = afterRequestSK(xml, returnSK);
		logger.info("//////sk返回：////"+ubl);
		if(ubl != null && (ubl.equals("5000") || ubl.equals("5004"))){//税控失败
			//TODO
			
			MessageProducer producer = session.createProducer(einvoiceKjfpfhMQ);
	        Message textMessage = session.createTextMessage(returnSK);
	        logger.info("///Listener sender ...///" +  returnSK);
	        producer.send(textMessage); 
			
		}else{
			while(!tsService.reqestTsPlat(ubl).equals("0000")){
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tsService.reqestTsPlat(ubl);
			}
		}
	}
	
	public String afterRequestSK(String xml, String returnSK){
		
		logger.info("/////////-----￥----税控接口返回：////////"+returnSK);

		logger.info("/////////-----￥----税控服务器返回的returncode:///////////"+InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>"));
		if("0000".equals(InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>"))){
			
			if("此发票已经开具过".equals( InvoiceUtil.getIntervalValue(returnSK, "<RETURNMSG>", "</RETURNMSG>"))){
				logger.info("///////-----￥----发票请求流水号：////////" + InvoiceUtil.getIntervalValue(returnSK, "<FPQQLSH>", "</FPQQLSH>") + ",此发票已经开具过。");
				return "5000";
			}
			
			int xtbs = xml.indexOf("<XTBS>");
			int jhkey = xml.indexOf("</JHKEY>");
			
			String shanchu = xml.substring(xtbs,jhkey+8);
			
			StringBuffer sb = new StringBuffer("<JQBH>");
			sb.append(InvoiceUtil.getIntervalValue(returnSK,"<JQBH>","</EWM>"));
			sb.append("</EWM>");
			
			xml = xml.replace(shanchu, sb.toString());
			
			int encoding1 = xml.indexOf("encoding=\"");
			int encoding2 = xml.indexOf("\"?>");
			
			String encode = xml.substring(encoding1 + "encoding=\"".length(), encoding2);
			xml = xml.replaceFirst(encode, "utf-8");
			
			long l1 = System.currentTimeMillis();
			String ubl = com.baiwang.einvoice.util.XmlToUbl.xmlToUbl(xml);
			long l2 = System.currentTimeMillis();
			long l3 = l2 - l1;
			logger.info("//////---------//////xml==>ubl---转换时间：/////////" + l3 + "//////------://////"+ubl);
			
			return ubl;
		}else{
			return "5004";
		}
		
	}

}
