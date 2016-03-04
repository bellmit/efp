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
import org.springframework.web.context.ServletConfigAware;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.service.ReportDetailService;
import com.baiwang.einvoice.util.ReportUtil;

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
	private ReportDetailService reportDetailService;
	
	@RequestMapping(value="init")
	public String initQuery(){
		return "fp/queryDetail";
	}
	
	@RequestMapping(value="query")
	public String queryReport(HttpServletRequest request){
		//获取查询条件
		String ddh4q = request.getParameter("ddh4q");
		request.setAttribute("ddh4q", ddh4q);
		String fplx4q = request.getParameter("fplx4q");
		String dateS = request.getParameter("beginDate");
		String dateE = request.getParameter("endDate");
		Map<String, Object> condition = new HashMap<>();
		condition.put("dateS", dateS);
		condition.put("dateE", dateE);
		condition.put("ddh4q", ddh4q);
		condition.put("fplx4q", fplx4q);
		List<ReportDetail> list = reportDetailService.selectByCondition(condition);//查询结果
		request.setAttribute("fpxxList", list);
		return "fp/queryDetail";
	}
	@RequestMapping(value="download")
	public void exportReport(HttpServletRequest request,HttpServletResponse response){
		String[] ddh4ept = request.getParameterValues("ddh4ept"); 
		List<ReportDetail> list = new ArrayList<>();
		if(null == ddh4ept || ddh4ept.length==0){
			//获取查询条件
			String ddh4q = request.getParameter("ddh4q");
			String fplx4q = request.getParameter("fplx4q");
			String dateS = request.getParameter("beginDate");
			String dateE = request.getParameter("endDate");
			Map<String, Object> condition = new HashMap<>();
			condition.put("dateS", dateS);
			condition.put("dateE", dateE);
			condition.put("ddh4q", ddh4q);
			condition.put("fplx4q", fplx4q);
			list = reportDetailService.selectByCondition(condition);//查询结果
		}else{
			for(String tmp:ddh4ept){
				list.add(reportDetailService.selectByPrimaryKey(tmp));
			}
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
