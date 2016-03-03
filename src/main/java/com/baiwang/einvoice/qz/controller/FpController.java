package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.ResultOfKp;
import com.baiwang.einvoice.qz.mq.EInvoiceSenders;
import com.baiwang.einvoice.qz.service.FpService;
import com.baiwang.einvoice.qz.service.IResultOfKpService;
import com.baiwang.einvoice.qz.utils.JAXBUtil;
import com.baiwang.einvoice.qz.utils.ValidateXML;
import com.baiwang.einvoice.qz.utils.XmlUtil;

@RequestMapping("einvoice")
@Controller
public class FpController {
	
	private static final Log logger = LogFactory.getLog(FpController.class);
	@Resource
	private EInvoiceSenders sender;
	@Resource
	private FpService fpService;
	
	@Resource
	private IResultOfKpService resultService;
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	@RequestMapping(value="kjfp",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String kjfp(String xml){
		
		String messageSelector = "JMSCorrelationID = '81ee2fb5-b40e-45f5-9e0d-1fd1bde9cc86'";
		TextMessage receive = (TextMessage) jmsTemplate2.receiveSelected(messageSelector );
        
        try {
			System.out.println(receive.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "开具发票";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String SaveKpInfo(String xml, HttpServletRequest request) throws UnsupportedEncodingException, JMSException{
		
		if( !ValidateXML.validateXml("wyyy.xsd", xml.getBytes("utf-8")) ){
			logger.error("xml不符合规则");
			return "xml不符合规则";
		}
		
		Business business = JAXBUtil.unmarshallObject(xml.getBytes("utf-8"));
		
		ResultOfKp result = resultService.queryResult(business.getCustomOrder().getDdhm());
		if(null != result && "0000".equals(result.getCode())){
			logger.warn("*********订单号：" + business.getCustomOrder().getDdhm() + "已经开票成功，返回。");
			return "0000";
		}
		
		try{
			sender.sendMessage(XmlUtil.toEInvoice(business.getREQUESTCOMMONFPKJ().getKpxx(), 
					business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx()).toString(), 
					business.getCustomOrder().getDdhm());
		}catch(Exception e){
			logger.error("*********订单号：" + business.getCustomOrder().getDdhm() + "网络异常");
			e.printStackTrace();
			return "网络异常";
		}

		fpService.saveXmlInfo(business);
		
		//从响应队列检索响应消息
		ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new EnumResposeMessageTask(business.getCustomOrder().getDdhm()));
		String success = "";
        try{
        	success = future.get(4, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {   
        	future.cancel(true);   
        } catch (ExecutionException e) {   
        	future.cancel(true);   
        } catch (TimeoutException e) {
        	
        	String requestURL = request.getRequestURL().toString();
    		String url = requestURL.substring(0,requestURL.lastIndexOf("/")) + "/query?corre=" + business.getCustomOrder().getDdhm();
    		
        	return "正在处理中,请稍后查询" + url;
        } finally {
            executor.shutdown();
        }
        
		return success;
	}
	
	@RequestMapping(value="query",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String query(@RequestParam String corre) {
		if(null == corre || "".equals(corre)){
			return "查询失败";
		}
		ResultOfKp result = resultService.queryResult(corre);
		
		if(null == result){
			return "正在处理中,请稍等...";
		}else if(null != result && "0000".equals(result.getCode())){
			logger.warn("*********订单号：" + corre + "已经开票成功，返回。");
			return "0000";
		}else{
			return result.getMsg();
		}
		
	}
	
	
	
}
