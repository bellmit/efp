package com.baiwang.einvoice.qz.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.PrintConfig;
import com.baiwang.einvoice.qz.beans.SkConfig;
import com.baiwang.einvoice.qz.service.ISetService;

@Controller
@RequestMapping("set")
public class SetController {
	
	private static final Log logger = LogFactory.getLog(SetController.class);
	
	@Resource
	private ISetService service;
	
	/*@RequestMapping("initsetting")
	@ResponseBody
	public Map<String, Object> initsetting(){
		
		Map<String, Object> map = service.initsetting();
		
		return map;
	}*/
	@RequestMapping("sksetting")
	@ResponseBody
	public Integer saveSksetting(SkConfig skconfig){
		logger.info("*******" + skconfig.getKpdq() + "," + skconfig.getUrl() + "," + skconfig.getPort());
		System.out.println("*******" + skconfig.getKpdq() + "," + skconfig.getUrl() + "," + skconfig.getPort());
		
		return service.saveSksetting(skconfig);
	}
	@RequestMapping("dysetting")
	@ResponseBody
	public Integer savedysetting(PrintConfig printconfig){
		logger.info("*******" + printconfig.getFplx() + "," + printconfig.getTopside() + "," + printconfig.getLeftside());
		System.out.println("*******" + printconfig.getFplx() + "," + printconfig.getTopside() + "," + printconfig.getLeftside());
		
		return service.savePrintsetting(printconfig);
	}

}
