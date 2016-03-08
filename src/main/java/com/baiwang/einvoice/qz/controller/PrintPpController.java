package com.baiwang.einvoice.qz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	public Map<String, Object> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh, String fplx,
			int requestPage, int pageSize, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(null != user){
			logger.info("***用户名：" + user.getUserName() + "," + beginDate +","+endDate +"," +kpdq +","+ zddh +","+fplx);
			/*if(null != kpdq && kpdq.equals(user.getUserType())){
				
			}*/
		}
		Map<String, Object> map = new HashMap<>();
		
		List<Map<String,String>> list = service.getPrintPpList(beginDate, endDate, kpdq, zddh, fplx, requestPage, pageSize);
		int size = service.queryCount(beginDate, endDate, kpdq, zddh, fplx);
		int pageCount = size%pageSize > 0 ? (size/pageSize +1) : size/pageSize;
		
		map.put("list", list);
		map.put("pageCount", pageCount);
		
		return map;
	}
	
	//连打
	@RequestMapping("printpps/printlist")
	@ResponseBody
	public Map<String, Object> getPrintPpsList(String beginDate, String endDate, String beginfphm, String endfphm, String fplx,
			int requestPage, int pageSize,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(null != user){
			logger.info("***用户名：" + user.getUserName() + "," + beginDate +","+endDate +"," +beginfphm +","+ endfphm );
			/*if(null != kpdq && kpdq.equals(user.getUserType())){
				
			}*/
		}
		Map<String, Object> map = new HashMap<>();
		
		List<Map<String,String>> list = service.getPrintPpsList(beginDate, endDate, beginfphm, endfphm, fplx);
		int size = list.size();
		
		requestPage = (requestPage - 1) * pageSize;
		List<Map<String,String>> _list = new ArrayList<>();
		for(int i = 0; i < pageSize && requestPage + i < size; i++){
			_list.add(list.get(requestPage + i));
		}
		
		int pageCount = size%pageSize > 0 ? (size/pageSize +1) : size/pageSize;
		map.put("list", _list);
		map.put("pageCount", pageCount);
		
		return map;
	}
	@RequestMapping("printpps/showDetail")
	@ResponseBody
	public List<Map<String,String>> showDetail(String begin, String end, String fplx){
		
		System.out.println(begin);
		System.out.println(end);
		System.out.println(fplx);
		
		return service.showDetail(begin, end, fplx);
	}
	
	@RequestMapping("print/getParameter")
	@ResponseBody
	public Map<String, Object> getParameter(String fplx, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		
		if(null == user){
			map.put("code", "-1");
			map.put("msg", "用户未登陆");
			return map;
		}else{
			String userType = Byte.toString(user.getUserType());
			SkConfig skconf = service.getSkParameter(userType);
			PrintConfig printconf = service.getPrintParameter(fplx);
			map.put("code", "0");
			map.put("msg", "成功");
			map.put("skconf", skconf);
			map.put("printconf", printconf);
		}
		
		return map;
	}
	
	@RequestMapping("print/savePrintResult")
	@ResponseBody
	public Integer savePrintResult(String fpqqlsh, String fpzt){
		int su = service.savePrintResult(fpqqlsh, fpzt);
		
		return su;
	}
	
	@RequestMapping("prints/getParameters")
	@ResponseBody
	public Map<String, Object> getParameters(String fplx, String beginfphm, String endfphm, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		
		if(null == user){
			map.put("code", "-1");
			map.put("msg", "用户未登陆");
			return map;
		}else{
			String userType = Byte.toString(user.getUserType());
			SkConfig skconf = service.getSkParameter(userType);
			PrintConfig printconf = service.getPrintParameter(fplx);
			List<Map<String,String>> list = service.getPrintsFphm(beginfphm, endfphm);
			map.put("code", "0");
			map.put("msg", "成功");
			map.put("skconf", skconf);
			map.put("printconf", printconf);
			map.put("list", list);
		}
		
		return map;
	}
	
	
}
