/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.Page;
import com.baiwang.einvoice.qz.service.FpService;
import com.baiwang.einvoice.util.InvoiceUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
  * @ClassName: InvoiceController
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月5日 下午1:59:38
  */

@Controller
@RequestMapping("/fpkj")
public class InvoiceController {

	private static final Log logger = LogFactory.getLog(InvoiceController.class);
	@Resource
	private FpService fpService;
	
	@RequestMapping("/plain")
	@ResponseBody
	public ModelAndView plainInvoiceList(HttpServletRequest request , HttpSession session , Page page){
		
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String zddh = request.getParameter("zddh");
		String kpdq = request.getParameter("kpdq");
		
		HashMap<String, String> param = new HashMap<>();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("zddh", zddh);
		param.put("kpdq", kpdq);
		
		String currentPage = request.getParameter("currentPage");
		if (!(null == currentPage || "".equals(currentPage))) {
			page.setPageIndex(Integer.parseInt(currentPage));
		}
		//List<Map<String, String>> kpxxList = fpService.getPlainList(param);
		PageList<HashMap<String, Object>> kpxxList = (PageList<HashMap<String, Object>>) fpService.listPlain(param,
				page.getPageIndex(), page.getPageSize());
		
		page.setPageSize(kpxxList.getPaginator().getLimit()); 
		page.setTotalCounts(kpxxList.getPaginator().getTotalCount());
		page.setTotalPages(kpxxList.getPaginator().getTotalPages());
		request.setAttribute("page", page);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fp/ptfpkj");
		mav.addObject("param", param);
		mav.addObject("kpxxList", kpxxList);
		return mav;
	}
	
	@RequestMapping("/special")
	@ResponseBody
	public ModelAndView specialInvoiceList(HttpServletRequest request , HttpSession session ,Page page){
		
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String zddh = request.getParameter("zddh");
		String kpdq = request.getParameter("kpdq");
		
		HashMap<String, String> param = new HashMap<>();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("zddh", zddh);
		param.put("kpdq", kpdq);
		
		//List<Map<String, String>> kpxxList = fpService.getSpecialList(param);
		String currentPage = request.getParameter("currentPage");
		if (!(null == currentPage || "".equals(currentPage))) {
			page.setPageIndex(Integer.parseInt(currentPage));
		}
		PageList<HashMap<String, Object>> kpxxList = (PageList<HashMap<String, Object>>) fpService.listPlain(param,
				page.getPageIndex(), page.getPageSize());
		
		page.setPageSize(kpxxList.getPaginator().getLimit()); 
		page.setTotalCounts(kpxxList.getPaginator().getTotalCount());
		page.setTotalPages(kpxxList.getPaginator().getTotalPages());
		request.setAttribute("page", page);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fp/zyfpkj");
		mav.addObject("param", param);
		mav.addObject("kpxxList", kpxxList);
		return mav;
	}
	
	@RequestMapping("/dzkp")
	@ResponseBody
	public HashMap<String, Object> dzkp(HttpServletRequest request) throws UnsupportedEncodingException{
	
		String[] fp =request.getParameter("arr").split(",");
		HashMap<String, Object> param = new HashMap<>();
		if(null==fp || fp.length<0){
			param.put("status", "error");
			param.put("msg", "请勾选至少一张发票！");
			return param;
		}
		logger.info("获取到需要开票的发票请求流水号：" + fp.toString());
		String[] xmlList = new String[fp.length];
		for(int i =0;i<fp.length;i++){
			String fpqqlsh = fp[i];
			if("".equals(fp[i])){
				continue;
			}
			Kpxx kpxx = fpService.getKpxxByFpqqlsh(fpqqlsh);
			List<Fpmx> fpmxList = fpService.getFpmxByFpqqlsh(fpqqlsh);
			String xml = fpService.getXml(kpxx, fpmxList);
			if(!"".equals(xml)){
				//开票
				xmlList[i] = xml;
			}
		}
		param.put("status", "success");
		param.put("xml", xmlList);
		
		return param;
	}
	
	@RequestMapping("/kp")
	@ResponseBody
	public HashMap<String, String> kp(HttpServletRequest request){
	
		String fpqqlsh =request.getParameter("ph");
		HashMap<String, String> param = new HashMap<>();
		if(StringUtils.isEmpty(fpqqlsh)){
			param.put("status", "error");
			param.put("msg", "请至少选择一张发票!");
			return param;
		}
		logger.info("获取到需要开票的发票请求流水号：" + fpqqlsh);
		Kpxx kpxx = fpService.getKpxxByFpqqlsh(fpqqlsh);
		List<Fpmx> fpmxList = fpService.getFpmxByFpqqlsh(fpqqlsh);
		String xml = fpService.getXml(kpxx, fpmxList);
		if(!"".equals(xml)){
			//开票
			param.put("status", "success");
			param.put("xml", xml);
		}
		return param;
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public String updateStatusByFpqqlsh(String fpqqlsh){
		if(StringUtils.isEmpty(fpqqlsh)){
			return "error";
		}
		fpService.updateFpztByFpqqlsh(fpqqlsh);
		return "success";
	}
	
	@RequestMapping("/callback")
	@ResponseBody
	public HashMap<String, Object> callback(String xml , String fpqqlsh){
		
		HashMap<String, Object> result = new HashMap<>();
		String returncode = InvoiceUtil.getIntervalValue(xml, "<returncode>", "</returncode>");
		String returnmsg = InvoiceUtil.getIntervalValue(xml, "<returnmsg>", "</returnmsg>");
		String jqbh = InvoiceUtil.getIntervalValue(xml, "<jqbh>", "</jqbh>");
		String fpdm = InvoiceUtil.getIntervalValue(xml, "<fpdm>", "</fpdm>");
		String fphm = InvoiceUtil.getIntervalValue(xml, "<fphm>", "</fphm>");
		String kprq = InvoiceUtil.getIntervalValue(xml, "<kprq>", "</kprq>");
		String skm = InvoiceUtil.getIntervalValue(xml, "<skm>", "</skm>");
		String jym = InvoiceUtil.getIntervalValue(xml, "<jym>", "</jym>");
		
		Kpxx fpxx = new Kpxx();
		fpxx.setFpqqlsh(fpqqlsh);
		fpxx.setResultcode(returncode);
		fpxx.setResultmsg(returnmsg);
		fpxx.setJqbh(jqbh);
		fpxx.setFpdm(fpdm);
		fpxx.setFphm(fphm);
		fpxx.setKprq(kprq);
		fpxx.setSkm(skm);
		fpxx.setJym(jym);
		
		if("0".equals(returncode)){
			fpxx.setFpzt("2");
		}else{
			fpxx.setFpzt("-1");
		}
		fpService.saveCallBackInfo(fpxx);
		
		result.put("status", "success");
		return result;
	}
}
