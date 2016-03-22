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
	public Map<String, Object> getPrintPpList(String beginDate, String endDate, String hyid, String fphm, String zddh, String shrdh, String fplx,
			int page, int rows, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<>();
		
		if(null != user){
			logger.info("***用户名：" + user.getCzymc() + "," + beginDate +","+endDate +"," +hyid +","+ zddh +","+fplx);
			
			List<Map<String,String>> list = service.getPrintPpList(beginDate, endDate, hyid, fphm, zddh, shrdh, fplx, page, rows, user.getNsrsbh());
			int size = service.queryCount(beginDate, endDate, hyid, fphm, zddh, shrdh, fplx, page, rows, user.getNsrsbh());
			//int pageCount = size%pageSize > 0 ? (size/pageSize +1) : size/pageSize;
			map.put("code", "0");
			map.put("rows", list);
			map.put("total", size);
		}else{
			logger.info("***用户名未登陆******");
			map.put("code", "-1");
			map.put("msg", "用户未登陆");
			map.put("rows", new ArrayList<Object>());
			map.put("total", 0);
			return map;
		}
		
		
		return map;
	}
	
	//连打
	@RequestMapping("printpps/printlist")
	@ResponseBody
	public Map<String, Object> getPrintPpsList(String beginDate, String endDate, String beginfphm, String endfphm, String fplx,
			int page, int rows, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<>();
		if(null != user){
			logger.info("***用户名：" + user.getCzymc() + "," + beginDate +","+endDate +"," +beginfphm +","+ endfphm );
			
			List<Map<String,Object>> list = service.getPrintPpsList(beginDate, endDate, beginfphm, endfphm, fplx, user.getNsrsbh());
			int size = list.size();
			
			page = (page - 1) * rows;
			List<Map<String,Object>> _list = new ArrayList<>();
			for(int i = 0; i < rows && page + i < size; i++){
				_list.add(list.get(page + i));
			}
			
			//int pageCount = size%rows > 0 ? (size/rows +1) : size/rows;
			map.put("code", "0");
			map.put("rows", _list);
			map.put("total", size);
		}else{
			map.put("code", "-1");
			map.put("msg", "用户未登陆");
			map.put("rows", new ArrayList<Object>());
			map.put("total", 0);
			return map;
		}
		
		
		return map;
	}
	@RequestMapping("printpps/showDetail")
	@ResponseBody
	public Map<String, Object> showDetail(String begin, String end, String fplx, int page, int rows, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<>();
		if(null != user){
			System.out.println(begin);
			System.out.println(end);
			System.out.println(fplx);
			List<Map<String,String>> list = service.showDetail(begin, end, fplx, page, rows, user.getNsrsbh());
			int size = service.queryDetailCount(begin, end, fplx, user.getNsrsbh());
			//int pageCount = size%rows > 0 ? (size/rows +1) : size/rows;
			
			map.put("code", "0");
			map.put("rows", list);
			map.put("total", size);
			return  map;
		}else{
			map.put("code", "-1");
			map.put("msg", "用户未登陆");
			map.put("rows", new ArrayList<Object>());
			map.put("total", 0);
			return map;
		}
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
			String code = "-2";
			if(null == user.getFplxdm()){
				logger.info("用户操作员代码：" + user.getCzydm() + "发票类型代码为null");
				code = "0";
			}else{
				String[] fplxdm = user.getFplxdm().split(",");
				if(null != fplxdm && fplxdm.length > 0){
					for(int i = 0; i < fplxdm.length; i++){
						if(fplx.equals(fplxdm[i])){
							code = "0";
						}
					}
				}
			}
			
			SkConfig skconf = service.getSkParameter(user.getNsrsbh());
			try{
				skconf.setKpzdbs(user.getKpddm());
			}catch(Exception e){
				logger.info("用户操作员代码：" + user.getCzydm() + "开票终端代码为null");
				e.printStackTrace();
			}
			PrintConfig printconf = service.getPrintParameter(fplx);
			map.put("code", code);
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
	
	@RequestMapping("prints/getPrintsList")
	@ResponseBody
	public Map<String, Object> getParameters(String fplx, String beginfphm, String endfphm, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		
		if(null == user){
			map.put("code", "-1");
			map.put("msg", "用户未登陆");
			return map;
		}else{
			//String userType = Byte.toString(user.getUserType());
			/*SkConfig skconf = service.getSkParameter("0");
			PrintConfig printconf = service.getPrintParameter(fplx);*/
			List<Map<String,String>> list = service.getPrintsFphm(beginfphm, endfphm);
			map.put("code", "0");
			map.put("msg", "成功");
			/*map.put("skconf", skconf);
			map.put("printconf", printconf);*/
			map.put("list", list);
		}
		
		return map;
	}
	
	
}
