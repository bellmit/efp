/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.mq.EInvoiceSenders;
import com.baiwang.einvoice.qz.mq.RequestTsListener;
import com.baiwang.einvoice.qz.mq.RequestTsSender;
import com.baiwang.einvoice.qz.service.IFpService;
import com.baiwang.einvoice.qz.service.IResultOfSkService;
import com.baiwang.einvoice.qz.service.skService.SkService;
import com.baiwang.einvoice.qz.service.skService.TsPlatService;
import com.baiwang.einvoice.qz.utils.InvoiceUtil;
import com.baiwang.einvoice.qz.utils.XmlUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
  * @ClassName: InvoiceController
  * @Description: 发票开具Controller
  * @author zhaowei
  * @date 2016年3月5日 下午1:59:38
  */

@Controller
@RequestMapping("/fpkj")
public class InvoiceController {

	private static final Log logger = LogFactory.getLog(InvoiceController.class);
	@Resource
	private IFpService fpService;
	@Resource
	private EInvoiceSenders sender;
	@Resource
	private IResultOfSkService resultService;
	
	@Resource
	private RequestTsSender tsSender;
	@Resource
	private SkService sKservice;
	@Resource
	private RequestTsListener requestTsListener;
	@Resource
	private TsPlatService tsService;
	
	/**
	  * @author zhaowei
	  * @Description: 普通纸质发票开具列表
	  * @param @param request
	  * @param @param session
	  * @param @param page
	  * @param @return  
	  * @return ModelAndView  
	  * @throws
	  * @date 2016年3月14日 下午4:37:48
	 */
	@RequestMapping("/plain")
	@ResponseBody
	public Map<String, Object> plainInvoiceList(HttpServletRequest request , HttpSession session , int page, int rows){
		Map<String, Object> map = new HashMap<>();
		User user = (User)session.getAttribute("user");
		if(user != null){
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			String zddh = request.getParameter("zddh");
			String hyid = request.getParameter("hyid");
			String shrdh = request.getParameter("shrdh");
			
			HashMap<String, String> param = new HashMap<>();
			param.put("beginDate", beginDate);
			param.put("endDate", endDate);
			param.put("zddh", zddh);
			param.put("hyid", hyid);
			param.put("shrdh", shrdh);
			param.put("nsrsbh", user.getNsrsbh());
			//List<Map<String, String>> kpxxList = fpService.getPlainList(param);
			PageList<HashMap<String, Object>> kpxxList = (PageList<HashMap<String, Object>>) fpService.listPlain(param,
					page, rows );
			
			map.put("rows", kpxxList);
			map.put("total", kpxxList.getPaginator().getTotalCount());
		}
		
		return map;
	}
	
	/***
	  * @author zhaowei
	  * @Description: 纸质专用发票开具列表
	  * @param @param request
	  * @param @param session
	  * @param @param page
	  * @param @return  
	  * @return ModelAndView  
	  * @throws
	  * @date 2016年3月14日 下午4:38:26
	 */
	@RequestMapping("/special")
	@ResponseBody
	public Map<String, Object> specialInvoiceList(HttpServletRequest request , HttpSession session , int page, int rows){
		
		Map<String, Object> map = new HashMap<>();
		User user = (User)session.getAttribute("user");
		if(user != null){
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String zddh = request.getParameter("zddh");
		String hyid = request.getParameter("hyid");
		String shrdh = request.getParameter("shrdh");
		
		HashMap<String, String> param = new HashMap<>();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("zddh", zddh);
		param.put("hyid", hyid);
		param.put("shrdh", shrdh);
		param.put("nsrsbh", user.getNsrsbh());
		
		PageList<HashMap<String, Object>> kpxxList = (PageList<HashMap<String, Object>>) fpService.listSpecial(param,
				page, rows);
		
		map.put("rows", kpxxList);
		map.put("total", kpxxList.getPaginator().getTotalCount());
		}
		return map;
	}
	
	/**
	  * @author zhaowei
	  * @Description: 多张发票开具
	  * @param @param request
	  * @param @return
	  * @param @throws UnsupportedEncodingException  
	  * @return HashMap<String,Object>  
	  * @throws
	  * @date 2016年3月14日 下午4:39:27
	 */
	@RequestMapping("/dzkp")
	@ResponseBody
	public HashMap<String, Object> dzkp(HttpServletRequest request) throws UnsupportedEncodingException{
	
		HashMap<String, Object> param = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			param.put("status", "-1");
			param.put("msg", "登录失效，请重新登录!");
			return param;
		}
		String[] fp =request.getParameter("arr").split(",");
		if(null==fp || fp.length<0){
			param.put("status", "-2");
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
			String xml = fpService.getXml(kpxx, fpmxList ,user.getKpddm());
			if(!"".equals(xml)){
				//开票
				xmlList[i] = xml;
			}
		}
		param.put("status", "success");
		param.put("xml", xmlList);
		
		return param;
	}
	
	/***
	  * @author zhaowei
	  * @Description: 单张发票开具
	  * @param @param request
	  * @param @return  
	  * @return HashMap<String,String>  
	  * @throws
	  * @date 2016年3月14日 下午4:40:04
	 */
	@RequestMapping("/kp")
	@ResponseBody
	public HashMap<String, String> kp(HttpServletRequest request){
		HashMap<String, String> param = new HashMap<>();
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			param.put("status", "-1");
			param.put("msg", "登录失效，请重新登录!");
			return param;
		}
		String fpqqlsh =request.getParameter("fpqqlsh");
		
		if(StringUtils.isEmpty(fpqqlsh)){
			param.put("status", "-2");
			param.put("msg", "请至少选择一张发票!");
			return param;
		}
		logger.info("获取到需要开票的发票请求流水号：" + fpqqlsh);
		Kpxx kpxx = fpService.getKpxxByFpqqlsh(fpqqlsh);
		List<Fpmx> fpmxList = fpService.getFpmxByFpqqlsh(fpqqlsh);
		String xml = fpService.getXml(kpxx, fpmxList,user.getKpddm());
		if(!"".equals(xml)){
			//开票
			param.put("status", "success");
			param.put("xml", xml);
		}
		return param;
	}
	
	/**
	  * @author zhaowei 
	  * @Description: 开具完成后更新发票状态为待打印
	  * @param @param fpqqlsh
	  * @param @return  
	  * @return String  
	  * @throws
	  * @date 2016年3月14日 下午4:41:00
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public String updateStatusByFpqqlsh(String fpqqlsh){
		if(StringUtils.isEmpty(fpqqlsh)){
			return "error";
		}
		fpService.updateFpztByFpqqlsh(fpqqlsh);
		return "success";
	}
	
	/***
	  * @author zhaowei
	  * @Description: 保持开票返回信息
	  * @param @param xml
	  * @param @param fpqqlsh
	  * @param @return  
	  * @return HashMap<String,Object>  
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	  * @throws
	  * @date 2016年3月14日 下午4:41:34
	 */
	@RequestMapping("/callback")
	@ResponseBody
	public HashMap<String, Object> callback(String xml , String fpqqlsh ,String xml_bw , String status) throws UnsupportedEncodingException, ParseException{
		
		HashMap<String, Object> result = new HashMap<>();
		String returncode = InvoiceUtil.getIntervalValue(xml, "<returncode>", "</returncode>");
		String returnmsg = InvoiceUtil.getIntervalValue(xml, "<returnmsg>", "</returnmsg>");
		String jqbh = InvoiceUtil.getIntervalValue(xml, "<jqbh>", "</jqbh>");
		String fpdm = InvoiceUtil.getIntervalValue(xml, "<fpdm>", "</fpdm>");
		String fphm = InvoiceUtil.getIntervalValue(xml, "<fphm>", "</fphm>");
		String kprq = InvoiceUtil.getIntervalValue(xml, "<kprq>", "</kprq>");
		String skm = InvoiceUtil.getIntervalValue(xml, "<skm>", "</skm>");
		String jym = InvoiceUtil.getIntervalValue(xml, "<jym>", "</jym>");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Kpxx fpxx = new Kpxx();
		fpxx.setFpqqlsh(fpqqlsh);
		fpxx.setResultcode(returncode);
		fpxx.setResultmsg(returnmsg);
		fpxx.setJqbh(jqbh);
		fpxx.setFpdm(fpdm);
		fpxx.setFphm(fphm);
		if(!StringUtils.isEmpty(kprq)){
			fpxx.setKprq(sdf2.format(sdf.parse(kprq)));
		}
		fpxx.setSkm(skm);
		fpxx.setJym(jym);
		
		fpxx.setFpzt(status);
		
		fpService.saveCallBackInfo(fpxx);
		
		Map<String, String> map = new HashMap<String, String>();
		String xml_ts = new String(XmlUtil.convert(xml_bw.getBytes("gbk")),"gbk");
		String return_xml_ts = XmlUtil.returnXml(fpxx);
		logger.info("纸质发票报文发票转换为："+xml_ts);
		map.put("xml", xml_ts);
		map.put("returnSK", return_xml_ts);
		tsSender.sendMessage(map);
		
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping("/ekp")
	@ResponseBody
	public Map<String, String> ekaipiao(Kpxx kpxx, HttpServletRequest request,HttpServletResponse response){
		String fpqqlsh = XmlUtil.random();
		kpxx.setFpqqlsh(fpqqlsh);
		kpxx.setFplx("026");
		kpxx.setKplx((byte)0);
		kpxx.setXsfmc("百旺亿城测试1");
		kpxx.setXsfnsrsbh("110109004357777777");
		kpxx.setXsfdz("499099991291");
		kpxx.setXsfdh("12321");
		kpxx.setXsfyhzh("中行499099991291");
		kpxx.setGmfdh("");
		kpxx.setGmfdz(request.getParameter("gmfdz"));
		kpxx.setYfpdm("");
		kpxx.setYfphm("");
		kpxx.setSjh(request.getParameter("telphone"));
		kpxx.setYx(request.getParameter("email"));
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
				fpmx.setFphxz("0");
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
				fpmx.setHsbz("0");
				fpmxList.add(fpmx);
			}
		}
		kpxx.setHjje((float)(Math.round(hjje*100))/100);
		kpxx.setHjse((float)(Math.round(hjse*100))/100);
		kpxx.setJshj((float)(Math.round((hjje+hjse)*100))/100);
		Map<String, String> result = resultService.queryResult(fpqqlsh, kpxx.getFplx());//根据两个订单号查
		if(null == result){
			if("026".equals(kpxx.getFplx())){
				try{
					logger.info("*****发票请求流水号为:" + fpqqlsh);
					String skResquestXml = XmlUtil.toEInvoice(kpxx,fpmxList).toString();
//					System.out.println("---------------"+XmlUtil.toEInvoice(kpxx,fpmxList).toString());
					//向税控发送请求报文
					String resultXml = sKservice.reqestSK(skResquestXml);
					//<RETURNCODE>0000</RETURNCODE><RETURNMSG>成功</RETURNMSG>
					String RETURNCODE = InvoiceUtil.getIntervalValue(resultXml, "<RETURNCODE>", "</RETURNCODE>");
					String RETURNMSG = InvoiceUtil.getIntervalValue(resultXml, "<RETURNMSG>", "</RETURNMSG>");
					System.out.println(resultXml);
					if("0000".equals(RETURNCODE)&&"成功".equals(RETURNMSG)){
						String ubl = requestTsListener.afterRequestSK(skResquestXml, resultXml);
						String reqestTsPlat = tsService.reqestTsPlat(ubl);
						if("0000".equals(reqestTsPlat)){
							System.out.println("电子发票开具成功！");
							map.put("returnCode", "0000");
							map.put("returnMsg", "发票已开具成功");
						}
					}else{
						map.put("returnMsg", RETURNMSG);
					}
				}catch(Exception e){
					logger.error("*********发票请求流水号：" + fpqqlsh + ",与税控连接异常");
					e.printStackTrace();
					map.put("returnCode", "4000");
					map.put("returnMsg", "网络异常");
				}
			}else{
				map.put("returnCode", "1234");
	        	map.put("returnMsg", "开票失败，未知错误！");
			}
		}else{
			map.put("returnCode", "abcd");
        	map.put("returnMsg", "开票失败，未知错误！");
		}
		return map;
//		try {
//			response.sendRedirect(request.getContextPath() + "/fpkj_e/fpkj.htm");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	public static String formatNum(String je){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(Double.parseDouble(je));
	}
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public User getUserInfo( HttpSession session){
		User user = (User) session.getAttribute("user");
		return user;
	}
}
