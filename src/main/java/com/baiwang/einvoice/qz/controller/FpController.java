package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.OrderDetail;
import com.baiwang.einvoice.qz.mq.EInvoiceSenders;
import com.baiwang.einvoice.qz.service.FpService;
import com.baiwang.einvoice.qz.service.IResultOfSkService;
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
	private IResultOfSkService resultService;
	
	@Autowired
    private JmsTemplate jmsTemplate2;
	
	@RequestMapping(value="save",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, String> SaveKpInfo(String xml, HttpServletRequest request) throws UnsupportedEncodingException, JMSException{
		Map<String, String> map = new HashMap<>();
		
		if( !ValidateXML.validateXml("wyyy.xsd", xml.getBytes()) ){
			logger.error("xml不符合规则");
			map.put("returnCode", "4000");
			map.put("returnMsg", "xml不符合规则");
			return map;
		}
		
		Business business = JAXBUtil.unmarshallObject(xml.getBytes());
		
		OrderDetail orderDetail = business.getOrderDetail();
		
		Kpxx kpxx = business.getREQUESTCOMMONFPKJ().getKpxx();
		
		String fpqqlsh = XmlUtil.random();
		
		List<Fpmx> list = business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx();
		System.out.println("订单号："+orderDetail.getZddh());
		
		/*ResultOfKp result = resultService.queryResult(customOrder.getDdhm(), "");*/
		Map<String, String> result = resultService.queryResult(kpxx.getZddh(), kpxx.getFddh(), kpxx.getFplx());//根据两个订单号查
		
		if(null == result){
			fpService.saveInfo(orderDetail, kpxx, list , fpqqlsh);
			
			//String correlationId = "";
			if("026".equals(kpxx.getFplx())){
				try{
//					UUID uuid = UUID.randomUUID();
//					correlationId = uuid.toString();
					logger.info("*****订单号为:" + orderDetail.getZddh()+"/"+orderDetail.getFddh() + "的关联id为:" + fpqqlsh);
					sender.sendMessage(XmlUtil.toEInvoice(kpxx,list).toString(), 
							fpqqlsh);
				}catch(Exception e){
					logger.error("*********订单号：" + orderDetail.getZddh()+"/"+orderDetail.getFddh() + ",sendMsg网络异常");
					e.printStackTrace();
					
					map.put("returnCode", "4000");
					map.put("returnMsg", "网络异常");
					return map;
				}
			
				//从响应队列检索响应消息
				ExecutorService executor = Executors.newSingleThreadExecutor();
		        Future<String> future = executor.submit(new EnumResposeMessageTask(orderDetail.getZddh()+orderDetail.getFddh(), fpqqlsh, jmsTemplate2, resultService));
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
//		        	String requestURL = request.getRequestURL().toString();
//		    		String url = requestURL.substring(0,requestURL.lastIndexOf("/")) + "/query?ddhm=" + orderDetail.getZddh()+orderDetail.getFddh();
		    		
		    		map.put("returnCode", "2000");
					map.put("returnMsg", "正在处理中,请稍后查询");
					return map;
		        } finally {
		            executor.shutdown();
		        }
		        
		        if(!"0000".equals(success)){
		        	map.put("returnCode", "4400");
		        	map.put("returnMsg", success);
		        }else{
		        	map.put("returnCode", "0000");
		        	map.put("returnMsg", "发票开具成功");
		        }
		        
				return map;
			}else{
				map.put("returnCode", "0000");
	        	map.put("returnMsg", "订单成功");
	        	return map;
			}
		}else if("0000".equals(result.get("returnCode"))){
			logger.warn("*********订单号：" + orderDetail.getZddh()+"/"+orderDetail.getFddh() + "已经开票成功，返回。");
			map.put("returnCode", "0000");
			map.put("returnMsg", "发票已开具成功");
			return map;
		}else{
			map.put("returnCode", result.get("returnCode"));
			map.put("returnMsg", result.get("returnMsg"));
			return map;
		}
		
		
	}
	
	@RequestMapping(value="receive",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String receive(String xml) throws UnsupportedEncodingException{
		
		if(null == xml || !ValidateXML.validateXml("wyyy.xsd", xml.getBytes("utf-8")) ){
			logger.error("xml不符合规则");
			return "xml不符合规则";
		}
		
		Business business = JAXBUtil.unmarshallObject(xml.getBytes("utf-8"));
		
		OrderDetail orderDetail = business.getOrderDetail();
		
		Kpxx kpxx = business.getREQUESTCOMMONFPKJ().getKpxx();
		
		String fpqqlsh = XmlUtil.random();
		
		List<Fpmx> list = business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx();
		
		fpService.saveInfo(orderDetail, kpxx , list , fpqqlsh);
		
		return "success";
	}
	
}
