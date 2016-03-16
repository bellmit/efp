package com.baiwang.einvoice.qz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.PrintConfig;
import com.baiwang.einvoice.qz.beans.SkConfig;
import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.service.ISetService;

@Controller
@RequestMapping("set")
public class SetController {
	
	private static final Log logger = LogFactory.getLog(SetController.class);
	
	@Resource
	private ISetService service;
	
	/*@RequestMapping("initsetting")
	@ResponseBody
	public Map<String, Object> initsetting(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		if(null == user || !user.getCzydm().equals("admin")){
			logger.warn("系统设置：用户未登陆");
			map.put("code", "-1");
		}else{
			logger.info("用户：" + user.getCzydm() + "加载系统设置");
			map = service.initsetting();
			map.put("code",	"0");
		}
		
		return map;
	}*/
	
	@RequestMapping("initsetting")
	@ResponseBody
	public Map<String, Object> initsetting(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		if(null == user || !user.getCzydm().equals("admin")){
			logger.warn("系统设置：用户未登陆");
			map.put("code", "-1");
		}else{
			logger.info("用户：" + user.getCzydm() + "加载系统设置");
			SkConfig config = service.initsetting(user.getCzydm());
			map.put("code",	"0");
			map.put("skConfig",	config);
		}
		
		return map;
	}
	@RequestMapping("saveSksetting")
	@ResponseBody
	public Integer saveSksetting(SkConfig skconfig){
		skconfig.setKpdq(0);
		logger.info("*******" + skconfig.getKpdq() + "," + skconfig.getUrl() + "," + skconfig.getPort());
		System.out.println("*******" + skconfig.getKpdq() + "," + skconfig.getUrl() + "," + skconfig.getPort());
		
		return service.saveSksetting(skconfig);
	}
	
	/*@RequestMapping("savesetting")
	@ResponseBody
	public Map<String, Object> savesetting(HttpServletRequest request, String servletip, String servletport){
		
		Map<String, Object> map = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		if(null == user || !user.getCzydm().equals("admin")){
			logger.warn("保存系统设置：用户未登陆");
			map.put("code", "-1");
		}else{
			logger.info("用户：" + user.getCzydm() + "保存系统设置" + ",ip:" + servletip + ",port:" + servletport);
			map = service.savesetting(servletip, servletport);
			map.put("code",	"0");
		}
		
		return map;
	}*/
	
	@RequestMapping("dysetting")
	@ResponseBody
	public Integer savedysetting(PrintConfig printconfig){
		logger.info("*******" + printconfig.getFplx() + "," + printconfig.getTopside() + "," + printconfig.getLeftside());
		System.out.println("*******" + printconfig.getFplx() + "," + printconfig.getTopside() + "," + printconfig.getLeftside());
		
		return service.savePrintsetting(printconfig);
	}

}
