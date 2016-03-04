package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
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
import com.baiwang.einvoice.qz.beans.CustomOrder;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
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
		
		String messageSelector = "JMSCorrelationID = '6158357d-1df5-470f-8223-f83892952f75'";
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
		
		String fpqqlsh = XmlUtil.random();
		CustomOrder customOrder = business.getCustomOrder();
		Kpxx kpxx = business.getREQUESTCOMMONFPKJ().getKpxx();
		kpxx.setFpqqlsh(fpqqlsh);
		List<Fpmx> list = business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx();
		System.out.println(customOrder.getDdhm());
		
		ResultOfKp result = resultService.queryResult(customOrder.getDdhm());
		if(null != result && "0000".equals(result.getCode())){
			logger.warn("*********订单号：" + customOrder.getDdhm() + "已经开票成功，返回。");
			return "0000";
		}
		
		String correlationId = "";
		try{
			UUID uuid = UUID.randomUUID();
			correlationId = uuid.toString();
			logger.info("*****订单号为:" + customOrder.getDdhm() + "的关联id为:" + correlationId);
			sender.sendMessage(XmlUtil.toEInvoice(kpxx,list).toString(), 
					correlationId);
		}catch(Exception e){
			logger.error("*********订单号：" + customOrder.getDdhm() + ",sendMsg网络异常");
			e.printStackTrace();
			return "网络异常";
		}

		fpService.saveInfo(customOrder, kpxx, list);
		
		//从响应队列检索响应消息
		ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new EnumResposeMessageTask(customOrder.getDdhm(), correlationId, jmsTemplate2, resultService));
		String success = "4400";
        try{
        	success = future.get(4, TimeUnit.SECONDS);
        	logger.info("响应队列检索响应消息:"+ success);
        }catch (InterruptedException e) {   
        	future.cancel(true);  
        	e.printStackTrace();
        } catch (ExecutionException e) {   
        	future.cancel(true);
        	e.printStackTrace();
        } catch (TimeoutException e) {
        	e.printStackTrace();
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
