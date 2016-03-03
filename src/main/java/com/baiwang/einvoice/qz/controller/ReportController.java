/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletConfigAware;

import com.baiwang.einvoice.qz.beans.ReportDetail;

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
	
	@RequestMapping(value="query")
	public String generateReport(HttpServletRequest request){
		//获取查询条件
		String dataS = request.getParameter("beginDate");
		String dataE = request.getParameter("endDate");
		if(null !=dataS || null !=dataE){
			List<ReportDetail> list = new ArrayList<>();//查询结果
			ReportDetail rd = new ReportDetail();
			rd.setDdh("123654789");
			list.add(rd);
			request.setAttribute("fpxxList", list);
		}
		return "fp/queryDetail";
	}
	@RequestMapping(value="download")
	public void exportReport(HttpServletRequest request,HttpServletResponse response){
		//获取查询条件
		String dataS = request.getParameter("dataS");
		String dataE = request.getParameter("dataE");
		List<ReportDetail> list = new ArrayList<>();//查询结果
		ReportDetail rd = new ReportDetail();
		rd.setDdh("123654789");
		list.add(rd);
		
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=a.xls");
		ServletOutputStream outputStream = null;
		FileInputStream inputStream = null;
		String rootPath = servletContext.getRealPath("/") ;
		File file = new File("C:\\Users\\Administrator\\git\\einvoice\\reports/wyen1.xls");
		try {
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			 byte[] b = new byte[1024];
             int length;
             while ((length = inputStream.read(b)) > 0) {
            	 outputStream.write(b, 0, length);
             }
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != outputStream){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
