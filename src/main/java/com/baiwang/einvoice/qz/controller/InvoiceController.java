/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.Page;
import com.baiwang.einvoice.qz.mq.EInvoiceSenders;
import com.baiwang.einvoice.qz.service.FpService;
import com.baiwang.einvoice.qz.service.IResultOfSkService;
import com.baiwang.einvoice.qz.utils.XmlUtil;
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
	@Resource
	private EInvoiceSenders sender;
	@Resource
	private IResultOfSkService resultService;
	
	@Autowired
    private JmsTemplate jmsTemplate2;
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
		PageList<HashMap<String, Object>> kpxxList = (PageList<HashMap<String, Object>>) fpService.listSpecial(param,
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
	
	@RequestMapping("/ekp")
	public void ekaipiao(Kpxx kpxx, HttpServletRequest request,HttpServletResponse response){
		String fpqqlsh = XmlUtil.random();
		kpxx.setFpqqlsh(fpqqlsh);
		kpxx.setFplx("026");
		kpxx.setKplx((byte)0);
		kpxx.setXsfmc("百旺股份6");
		kpxx.setXsfnsrsbh("11010800000000000006");
		kpxx.setXsfdz("499099991291");
		kpxx.setXsfdh("12321");
		kpxx.setXsfyhzh("中行499099991291");
		kpxx.setYfpdm("");
		kpxx.setYfphm("");
		Map<String, String> map = new HashMap<>();
		List<Fpmx> fpmxList = new ArrayList<>();
		String [] xmmc = request.getParameterValues("xmmc");
		String [] ggxh = request.getParameterValues("ggxh");
		String [] dw = request.getParameterValues("dw");
		String [] xmsl = request.getParameterValues("xmsl");
		String [] xmdj = request.getParameterValues("xmdj");
		String [] xmje = request.getParameterValues("xmje");
		String [] sl = request.getParameterValues("sl");
		String [] se = request.getParameterValues("se");
//		String [] hsbz = request.getParameterValues("hsbz");
		float hjje = 0f;
		float hjse = 0f;
		if(null != xmmc){
			for(int i=0;i<xmmc.length;i++){
				Fpmx fpmx = new Fpmx();
				fpmx.setFphxz(false);
				fpmx.setXmmc(xmmc[i]);
				fpmx.setGgxh(ggxh[i]);
				fpmx.setDw(dw[i]);
				fpmx.setXmsl(Float.valueOf(formatNum(xmsl[i])));
				fpmx.setXmdj(Float.valueOf(formatNum(xmdj[i])));
				fpmx.setXmje( Float.valueOf(formatNum(xmje[i])));
				hjje+=Float.valueOf(xmje[i]);
				fpmx.setSl(Float.valueOf(formatNum(sl[i])));
				fpmx.setSe(Float.valueOf(formatNum(se[i])));
				hjse+=Float.valueOf(formatNum(se[i]));
				fpmx.setHsbz(false);
				fpmxList.add(fpmx);
			}
		}
		kpxx.setHjje((float)(Math.round(hjje*100))/100);
		kpxx.setHjse((float)(Math.round(hjse*100))/100);
		kpxx.setJshj((float)(Math.round((hjje+hjse)*100))/100);
		Map<String, String> result = resultService.queryResult(kpxx.getZddh(), kpxx.getFddh(), kpxx.getFplx());//根据两个订单号查
		
		if(null == result){
			
			String correlationId = "";
			if("026".equals(kpxx.getFplx())){
				try{
					UUID uuid = UUID.randomUUID();
					correlationId = uuid.toString();
					logger.info("*****订单号为:" + fpqqlsh + "的关联id为:" + correlationId);
					System.out.println("---------------"+XmlUtil.toEInvoice(kpxx,fpmxList).toString());
					sender.sendMessage(XmlUtil.toEInvoice(kpxx,fpmxList).toString(), 
							correlationId);
				}catch(Exception e){
					logger.error("*********订单号：" + fpqqlsh + ",sendMsg网络异常");
					e.printStackTrace();
					
					map.put("returnCode", "4000");
					map.put("returnMsg", "网络异常");
				}
			
				//从响应队列检索响应消息
				ExecutorService executor = Executors.newSingleThreadExecutor();
		        Future<String> future = executor.submit(new EnumResposeMessageTask(fpqqlsh, correlationId, jmsTemplate2, resultService));
				String success = "4400";
		        try{
		        	success = future.get(4, TimeUnit.SECONDS);
		        	logger.info("响应队列检索响应消息:"+ success);
		        }catch (InterruptedException e) {
		        	future.cancel(true);
		        	e.printStackTrace();
		        } catch (ExecutionException e) {
		        	future.cancel(true);
		        	e.printStackTrace();
		        } catch (TimeoutException e) {
		        	e.printStackTrace();
		        	String requestURL = request.getRequestURL().toString();
		    		String url = requestURL.substring(0,requestURL.lastIndexOf("/")) + "/query?ddhm=" + fpqqlsh;
		    		
		    		map.put("returnCode", "2000");
					map.put("returnMsg", "正在处理中,请稍后查询" + url);
		        } finally {
		            executor.shutdown();
		        }
		        
		        if(!"0000".equals(success)){
		        	map.put("returnCode", "4400");
		        	map.put("returnMsg", success);
		        }else{
		        	map.put("returnCode", "0000");
		        	map.put("returnMsg", "发票开具成功");
		        }
		        
			}else{
				map.put("returnCode", "0000");
	        	map.put("returnMsg", "订单成功");
			}
		}else if("0000".equals(result.get("returnCode"))){
			logger.warn("*********订单号：" + fpqqlsh + "已经开票成功，返回。");
			map.put("returnCode", "0000");
			map.put("returnMsg", "发票已开具成功");
		}else{
			map.put("returnCode", result.get("returnCode"));
			map.put("returnMsg", result.get("returnMsg"));
		}
		
			 try {
				response.sendRedirect(request.getContextPath() + "/fpkj_e/fpkj.htm");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static String formatNum(String je){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(Double.parseDouble(je));
	}
}
