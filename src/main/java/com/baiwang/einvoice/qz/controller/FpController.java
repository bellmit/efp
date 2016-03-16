package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.OrderDetail;
import com.baiwang.einvoice.qz.beans.SkConfig;
import com.baiwang.einvoice.qz.mq.EInvoiceSenders;
import com.baiwang.einvoice.qz.service.IFpService;
import com.baiwang.einvoice.qz.service.IPrintPpService;
import com.baiwang.einvoice.qz.service.IResultOfSkService;
import com.baiwang.einvoice.qz.utils.JAXBUtil;
import com.baiwang.einvoice.qz.utils.ValidateXML;
import com.baiwang.einvoice.qz.utils.XmlUtil;

@RequestMapping("einvoice")
@Controller
public class FpController {
	
	private static final Log logger = LogFactory.getLog(FpController.class);
	@Resource
	private EInvoiceSenders sender;
	@Resource
	private IFpService fpService;
	
	@Resource
	private IResultOfSkService resultService;
	@Resource
	private IPrintPpService printSerive;
	
	/**
	  * @author zhaowei
	  * @Description: 接收无忧纸质发票报文，并保持到数据库
	  * @param @param xml
	  * @param @return
	  * @param @throws UnsupportedEncodingException  
	  * @return String  
	  * @throws
	  * @date 2016年3月14日 下午4:31:18
	 */
	@RequestMapping(value="receive",method=RequestMethod.POST)
	@ResponseBody
	public String receive(String xml) throws UnsupportedEncodingException{
		
		if(null == xml || !ValidateXML.validateXml("wyyy.xsd", xml.getBytes("gbk")) ){
			logger.error("xml不符合规则");
			return "xml不符合规则";
		}
		
		Business business = JAXBUtil.unmarshallObject(xml.getBytes("gbk"));
		
		OrderDetail orderDetail = business.getOrderDetail();
		
		Kpxx kpxx = business.getREQUESTCOMMONFPKJ().getKpxx();
		
		String fpqqlsh = XmlUtil.random();
		
		List<Fpmx> list = business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx();
		
		fpService.saveInfo(orderDetail, kpxx , list , fpqqlsh);
		
		return "success";
	}
	//查询已经开具并打印过的普通发票
	@RequestMapping(value="ptfpzf_q")
	@ResponseBody
	public Map<String, Object> queryPtfp(HttpServletRequest request,int page, int rows){
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String hyid4q = request.getParameter("hyid4q");
		String fphm4q = request.getParameter("fphm4q");
		String ddh4q = request.getParameter("ddh4q");
		String sjh4q = request.getParameter("sjh4q");
		Map<String, Object> param = new HashMap<>();
		param.put("beginDate", beginDate);
		param.put("endDate",endDate );
		param.put("hyid4q",hyid4q );
		param.put("fphm4q", fphm4q);
		param.put("ddh4q",ddh4q );
		param.put("sjh4q",sjh4q );
		param.put("startRow", (page-1)*rows);
		param.put("rows", rows);
		List<Map<String, Object>> fpxxList = fpService.getPlainList4zf(param);
		int size = fpService.getPlainList4zfCount(param);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", fpxxList);
		result.put("total", size);
		return result;
	}
	//查询已经开具并打印过的专用发票
	@RequestMapping(value="zyfpzf_q")
	@ResponseBody
	public Map<String, Object> queryZyfp(HttpServletRequest request,int page, int rows){
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String hyid4q = request.getParameter("hyid4q");
		String fphm4q = request.getParameter("fphm4q");
		String ddh4q = request.getParameter("ddh4q");
		String sjh4q = request.getParameter("sjh4q");
		Map<String, Object> param = new HashMap<>();
		param.put("beginDate", beginDate);
		param.put("endDate",endDate );
		param.put("hyid4q",hyid4q );
		param.put("fphm4q", fphm4q);
		param.put("ddh4q",ddh4q );
		param.put("sjh4q",sjh4q );
		param.put("startRow", (page-1)*rows);
		param.put("rows", rows);
		List<Map<String, Object>> fpxxList = fpService.getSpecialList4zf(param);
		int size = fpService.getSpecialList4zfCount(param);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", fpxxList);
		result.put("total", size);
		return result;
	}
	//普通发票作废
	@RequestMapping(value="ptfpzf")
	@ResponseBody
	public Map<String, Object> cancel_pt(String lsh){
		Map<String, Object> result = new HashMap<>();
		//查询发票信息
		Kpxx kpxx = fpService.getKpxxByFpqqlsh(lsh);
		result.put("kpxx", kpxx);
		//查询SK配置信息
		SkConfig skconfig = printSerive.getSkParameter("0");
		result.put("skconfig", skconfig);
		return result;
	}
	//专用发票作废
	@RequestMapping(value="zyfpzf")
	@ResponseBody
	public Map<String, Object> cancel_zy(String lsh){
		Map<String, Object> result = new HashMap<>();
		//查询发票信息
		Kpxx kpxx = fpService.getKpxxByFpqqlsh(lsh);
		result.put("kpxx", kpxx);
		//查询SK配置信息
		SkConfig skconfig = printSerive.getSkParameter("0");
		result.put("skconfig", skconfig);
		return result;
	}
	
	
	//更新发票状态->作废
	@RequestMapping(value="updateFpzt2zf")
	@ResponseBody
	public String updateFpzt2zf(String lsh){
		int result = fpService.zfByFpqqlsh(lsh);
		if(result==1){
			return "0";
		}else{
			return "1";
		}
	}
}
