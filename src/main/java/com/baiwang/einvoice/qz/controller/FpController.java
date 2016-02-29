package com.baiwang.einvoice.qz.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.POST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.dao.FpmxMapper;
import com.baiwang.einvoice.qz.dao.KpxxMapper;
import com.baiwang.einvoice.qz.utils.JAXBUtil;

@RequestMapping("einvoice")
@Controller
public class FpController {
	
	@Resource
	private KpxxMapper dao;
	@Resource
	private FpmxMapper fpmxDao;
	@RequestMapping("kjfp")
	@ResponseBody
	@POST
	public String kjfp(String xml){
		
		
		return "开具发票";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public String SaveKpInfo(String xml) throws UnsupportedEncodingException{
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
		
		return "开具发票";
	}
	
	
}
