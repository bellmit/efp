package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.dao.FpmxMapper;
import com.baiwang.einvoice.qz.dao.KpxxMapper;
import com.baiwang.einvoice.qz.mq.EInvoceKjfpfhListener;
import com.baiwang.einvoice.qz.mq.EInvoiceSenders;
import com.baiwang.einvoice.qz.utils.JAXBUtil;
import com.baiwang.einvoice.qz.utils.XmlCheck;

@RequestMapping("einvoice")
@Controller
public class FpController {
	
	@Resource
	private KpxxMapper dao;
	@Resource
	private FpmxMapper fpmxDao;
	@Resource
	private EInvoiceSenders sender;
	@RequestMapping("kjfp")
	@ResponseBody
	@POST
	public String kjfp(String xml){
		
		
		return "开具发票";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String SaveKpInfo(String xml, HttpServletRequest request) throws UnsupportedEncodingException, JMSException{
		
		String chk = XmlCheck.checkXml(xml);
		if( !"0000".equals(chk) ){
			return chk;
		}
		
		UUID uuid = UUID.randomUUID();
		String correlationId = uuid.toString();
		sender.sendMessage(xml, correlationId);

		Business business = JAXBUtil.unmarshallObject(xml.getBytes("gbk"));
		String fpqqlsh = business.getREQUESTCOMMONFPKJ().getKpxx().getFpqqlsh();
		dao.insert(business.getREQUESTCOMMONFPKJ().getKpxx());
		
		List<Fpmx> list = business.getREQUESTCOMMONFPKJ().getCOMMONFPKJXMXXS().getFpmx();
		if(list.size()>0){
			for(Fpmx fpmx: list){
				fpmx.setFpqqlsh(fpqqlsh);
			}
			
			fpmxDao.insertFromList(list);
		}
		
		Map<String, String> map = EInvoceKjfpfhListener.getMap();
		
		String requestURL = request.getRequestURL().toString();
		String url = requestURL.substring(0,requestURL.lastIndexOf("/")) + "/query?corre=" + correlationId;
		
		
		if(null != map.get(correlationId)){
			return map.get(correlationId);
		}else{
			return "正在处理中,请查询" + url;
		}
	}
	
	@RequestMapping(value="query",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String query(@RequestParam String corre) {
		if(null == corre || "".equals(corre)){
			return "查询失败";
		}
		Map<String, String> map = EInvoceKjfpfhListener.getMap();
		if(null != map.get(corre)){
			return map.get(corre);
		}else{
			return "正在处理中,请稍等...";
		}
		
	}
	
	
	
}
