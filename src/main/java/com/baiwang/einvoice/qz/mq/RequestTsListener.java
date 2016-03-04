package com.baiwang.einvoice.qz.mq;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.baiwang.einvoice.service.skService.SkService;
import com.baiwang.einvoice.service.skService.TsPlatService;
import com.baiwang.einvoice.util.InvoiceUtil;

@SuppressWarnings("rawtypes")
public class RequestTsListener  implements SessionAwareMessageListener{

	private Log logger = LogFactory.getLog(RequestTsListener.class);
	
	@Autowired
    private JmsTemplate jmsTemplate3;
	
	@Resource
	private TsPlatService tsService;
	
	@Resource
	private Destination requestTsQuery;
	
	public void onMessage(Message message, Session session) throws JMSException {
		ActiveMQMapMessage msg = (ActiveMQMapMessage)message; 
		Map map = msg.getContentMap();
		String xml = (String) map.get("xml");
		String returnSK = (String) map.get("returnSK");
		
		String ubl = afterRequestSK(xml, returnSK);
		
		logger.info("***********转换ubl****************" + ubl);
		
		tsService.reqestTsPlat(ubl);
			
	}
	
	public String afterRequestSK(String xml, String returnSK){
		
		logger.info("/////////-----￥----税控接口返回：////////"+returnSK);

		logger.info("/////////-----￥----税控服务器返回的returncode:///////////"+InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>"));
		if("0000".equals(InvoiceUtil.getIntervalValue(returnSK,"<RETURNCODE>","</RETURNCODE>"))){
			
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
			return returnSK;
		}
		
	}
	

}
