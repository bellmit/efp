package com.baiwang.einvoice.qz.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.service.IPrintPpService;

@Controller
@RequestMapping("")
public class PrintPpController {
	
	private static final Log logger = LogFactory.getLog(PrintPpController.class);
	
	@Resource
	private IPrintPpService service;

	//单打
	@RequestMapping("printpp/printlist")
	@ResponseBody
	public List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh, String fplx, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(null != user){
			logger.info("***用户名：" + user.getUserName() + "," + beginDate +","+endDate +"," +kpdq +","+ zddh +","+fplx);
			/*if(null != kpdq && kpdq.equals(user.getUserType())){
				
			}*/
		}
		
		List<Map<String,String>> fapiaoVo = service.getPrintPpList(beginDate, endDate, kpdq, zddh, fplx);
		System.out.println(fapiaoVo.size());
		
		return fapiaoVo;
	}
	
	//连打
	@RequestMapping("printpps/printlist")
	@ResponseBody
	public List<Map<String,String>> getPrintPpsList(String beginDate, String endDate, String beginfphm, String endfphm, String fplx, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(null != user){
			logger.info("***用户名：" + user.getUserName() + "," + beginDate +","+endDate +"," +beginfphm +","+ endfphm );
			/*if(null != kpdq && kpdq.equals(user.getUserType())){
				
			}*/
		}
		
		List<Map<String,String>> fapiaoVo = service.getPrintPpsList(beginDate, endDate, beginfphm, endfphm, fplx);
		System.out.println(fapiaoVo.size());
		
		return fapiaoVo;
	}
	@RequestMapping("printpps/showDetail")
	@ResponseBody
	public List<Map<String,String>> showDetail(String begin, String end, String fplx){
		
		System.out.println(begin);
		System.out.println(end);
		System.out.println(fplx);
		
		return service.showDetail(begin, end, fplx);
	}
	
	
	
}
