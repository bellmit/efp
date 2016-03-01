package com.baiwang.einvoice.qz.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private EInvoceKjfpfhListener returnMsg;
	
	@Resource
	private SkService service;

	@Resource
	private TsPlatService tsService;
	
	@Resource
	private EInvoiceSenders sender;
	
	@RequestMapping(value="kjfp",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String kjfp(@RequestParam String xml){
		
		System.out.println("接收xml:"+xml);
//		String reqestSK = service.reqestSK(xml);
//		String reqestTS = tsService.reqestTsPlat(xml);
		
		sender.sendMessageOfKjfp(xml);
		try {
			Thread.currentThread().sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String reMsg = returnMsg.getReturnMsg();
		
		System.out.println("税控返回："+reMsg);
		
		return reMsg;
	}
	
	
	
}
