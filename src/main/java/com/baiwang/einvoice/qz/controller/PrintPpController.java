package com.baiwang.einvoice.qz.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.service.IPrintPpService;

@Controller
@RequestMapping("printpp")
public class PrintPpController {
	
	@Resource
	private IPrintPpService service;

	@RequestMapping("printlist")
	@ResponseBody
	public List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh){
		System.out.println(beginDate);
		System.out.println(endDate);
		System.out.println(kpdq);
		System.out.println(zddh);
		
		List<Map<String,String>> fapiaoVo = service.getPrintPpList(beginDate, endDate, kpdq, zddh);
		System.out.println(fapiaoVo.size());
		
		return fapiaoVo;
	}
	
}
