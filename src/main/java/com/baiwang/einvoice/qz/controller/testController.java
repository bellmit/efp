package com.baiwang.einvoice.qz.controller;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.mq.EInvoceKjfpfhListener;
import com.baiwang.einvoice.qz.mq.EInvoiceSenders;
import com.baiwang.einvoice.service.skService.SkService;
import com.baiwang.einvoice.service.skService.TsPlatService;

@RequestMapping("ein")
@Controller
public class testController {
	
	@Resource
	private SkService service;

	@Resource
	private TsPlatService tsService;
	
	@Resource
	private EInvoiceSenders sender;
	
	@Autowired(required=true)
	@Qualifier("einvoiceMQ")
	private ActiveMQQueue einvoiceMQ;
	
	@RequestMapping(value="kjfp",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String kjfp(@RequestParam String xml){
		long l1 = System.currentTimeMillis();
		System.out.println("接收xml:"+xml);
//		String reqestSK = service.reqestSK(xml);
//		String reqestTS = tsService.reqestTsPlat(xml);
		UUID uuid = UUID.randomUUID();
		String correlationId = uuid.toString();
		System.out.println("request ...correlationId////" + correlationId);
		sender.sendMessage(einvoiceMQ, xml, correlationId);
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Map<String, String> map = EInvoceKjfpfhListener.getMap();
        System.out.println("~~22~~~~~~~~~~~~~~/"+map.get(correlationId));
		
        long l2 = System.currentTimeMillis();
        long l3 = l2- l1;
        System.out.println("请求时间：" + l3);
        
//		System.out.println("税控返回："+reMsg);
		if(null != map.get(correlationId)){
			return map.get(correlationId);
		}else{
			return "正在处理中";
		}
	}
	
	
	
}
