/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.beans.ReportTotal;
import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.service.ReportService;
import com.baiwang.einvoice.qz.utils.ReportUtil;

/**
  * @ClassName: ReportController
  * @Description: 报表controller
  * @author Administrator
  * @date 2016年3月2日 上午11:39:02
  */
@Controller
@RequestMapping(value="report")
public class ReportController implements ServletConfigAware {
	@Resource
	private ServletContext servletContext;
	@Resource
	private ReportService reportService;
	
	@RequestMapping(value="init")
	public String initQuery(){
		return "fp/queryDetail";
	}
	
	@RequestMapping(value="queryFPlist")
	@ResponseBody
	public Map<String, Object> queryReport(HttpServletRequest request,int page, int rows){
		User user = (User)request.getSession().getAttribute("user");
		//获取查询条件
		String ddh4q = request.getParameter("ddh4q");
		String fplx4q = request.getParameter("fplx4q");
		String kpdq4q = request.getParameter("kpdq4q");
		String fpzl4q = request.getParameter("fpzl4q");
		String fptt4q = request.getParameter("fptt4q");
		String dateS = request.getParameter("beginDate");
		String dateE = request.getParameter("endDate");
		request.setAttribute("ddh4save", ddh4q);
		request.setAttribute("fplx4save", fplx4q);
		request.setAttribute("kpdq4save", kpdq4q);
		request.setAttribute("fpzl4save", fpzl4q);
		request.setAttribute("fptt4save", fptt4q);
		request.setAttribute("dateS4save", dateS);
		request.setAttribute("dateE4save", dateE);
		Map<String, Object> condition = new HashMap<>();
		condition.put("dateS", dateS);
		condition.put("dateE", dateE);
		condition.put("ddh4q", ddh4q);
		condition.put("fplx4q", fplx4q);
		condition.put("kpdq4q", kpdq4q);
		condition.put("fpzl4q", fpzl4q);
		condition.put("fptt4q", fptt4q);
		condition.put("nsrsbh", user.getNsrsbh());
		condition.put("startRow", (page-1)*rows);
		condition.put("rows", rows);
		List<ReportDetail> list = reportService.getFpListByCondition(condition);//查询结果
		int size = reportService.getFpCount(condition);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", list);
		result.put("total", size);
		return result;
	}
	@RequestMapping(value="download")
	public void exportReport(HttpServletRequest request,HttpServletResponse response){
		
		String lshs = request.getParameter("fplshs");
		String[] lsh4ept=null;
		if(null != lshs && lshs.trim().length()>0){
			lsh4ept = lshs.split(",");
		}
		List<ReportDetail> list = new ArrayList<>();
		if(null != lsh4ept && lsh4ept.length>0){
			for(String tmp:lsh4ept){
				list.add(reportService.getFpByLSH(tmp));
			}
		}else{
			//获取查询条件
			String dateS = request.getParameter("dateS4save");
			String dateE = request.getParameter("dateE4save");
			String ddh4q = request.getParameter("ddh4save");
			String fplx4q = request.getParameter("fplx4save");
			String kpdq4q = request.getParameter("kpdq4save");
			String fpzl4q = request.getParameter("fpzl4save");
			String fptt4q = request.getParameter("fptt4save");
			Map<String, Object> condition = new HashMap<>();
			condition.put("dateS", dateS);
			condition.put("dateE", dateE);
			condition.put("ddh4q", ddh4q);
			condition.put("fplx4q", fplx4q);
			condition.put("kpdq4q", kpdq4q);
			condition.put("fpzl4q", fpzl4q);
			condition.put("fptt4q", fptt4q);
			User user = (User)request.getSession().getAttribute("user");
			condition.put("nsrsbh", user.getNsrsbh());
			list = reportService.getFpListByCondition4d(condition);//查询结果
		}
		//生成excel
		if(list.size()>0){
			try {
				HSSFWorkbook wb = ReportUtil.exportExcel(list);
				response.setContentType("application/ms-excel");
				response.setHeader("Content-Disposition", "attachment;Filename=new.xls");
				OutputStream os = response.getOutputStream();
				wb.write(os);
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	  * @author ldm
	  * @Description: 查询发票统计列表
	  * @param @param request
	  * @param @param page
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2016年3月14日 下午4:36:10
	  */
	@RequestMapping(value="queryFPstat")
	@ResponseBody
	public Map<String, Object> queryStatReport(HttpServletRequest request,int page, int rows){
		//获取查询条件
		String fplx4q = request.getParameter("fplx4q");
		String kpdq4q = request.getParameter("kpdq4q");
		String fpzl4q = request.getParameter("fpzl4q");
		String fptt4q = request.getParameter("fptt4q");
		String dateS = request.getParameter("beginDate");
		String dateE = request.getParameter("endDate");
		request.setAttribute("fplx4save", fplx4q);
		request.setAttribute("kpdq4save", kpdq4q);
		request.setAttribute("fpzl4save", fpzl4q);
		request.setAttribute("fptt4save", fptt4q);
		request.setAttribute("dateS4save", dateS);
		request.setAttribute("dateE4save", dateE);
		Map<String, Object> condition = new HashMap<>();
		condition.put("dateS", dateS);
		condition.put("dateE", dateE);
		condition.put("fplx4q", fplx4q);
		condition.put("kpdq4q", kpdq4q);
		condition.put("fpzl4q", fpzl4q);
		condition.put("fptt4q", fptt4q);
		User user = (User)request.getSession().getAttribute("user");
		condition.put("nsrsbh", user.getNsrsbh());
		condition.put("startRow",  (page-1)*rows);
		condition.put("rows", rows);
		
		List<ReportTotal> fpxxList = reportService.getFpStatListByCondition(condition);
		int size = reportService.getFpStatCount(condition);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", fpxxList);
		result.put("total", size);
		return result;
	}
	@RequestMapping(value="exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response){
		String lshs = request.getParameter("fplshs");
		String[] lsh4ept=null;
		if(null != lshs  && lshs.trim().length()>0){
			lsh4ept = lshs.split(",");
		}
		List<ReportTotal> list = new ArrayList<>();
		if(null != lsh4ept && lsh4ept.length>0){
			for(String tmp:lsh4ept){
				list.add(reportService.getFpStatByLSH(tmp));
			}
		}else{
			//获取查询条件
			String dateS = request.getParameter("dateS4save");
			String dateE = request.getParameter("dateE4save");
			String ddh4q = request.getParameter("ddh4save");
			String fplx4q = request.getParameter("fplx4save");
			String kpdq4q = request.getParameter("kpdq4save");
			String fpzl4q = request.getParameter("fpzl4save");
			String fptt4q = request.getParameter("fptt4save");
			Map<String, Object> condition = new HashMap<>();
			condition.put("dateS", dateS);
			condition.put("dateE", dateE);
			condition.put("ddh4q", ddh4q);
			condition.put("fplx4q", fplx4q);
			condition.put("kpdq4q", kpdq4q);
			condition.put("fpzl4q", fpzl4q);
			condition.put("fptt4q", fptt4q);
			User user = (User)request.getSession().getAttribute("user");
			condition.put("nsrsbh", user.getNsrsbh());
			list = reportService.getFpListByCondition4e(condition);//查询结果
		}
		
		//生成excel
		if(list.size()>0){
			try {
				HSSFWorkbook wb = ReportUtil.exportExcel_stat(list);
				response.setContentType("application/ms-excel");
				response.setHeader("Content-Disposition", "attachment;Filename=new.xls");
				OutputStream os = response.getOutputStream();
				wb.write(os);
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param servletConfig  
	  * @throws
	  * @date 2016年3月3日 下午4:12:44
	  */
	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletContext = servletConfig.getServletContext();
		
	}
}
