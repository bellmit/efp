package com.baiwang.einvoice.qz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.dao.PrintDaoMapper;
import com.baiwang.einvoice.qz.service.IPrintPpService;

@Service
public class PrintPpServiceImpl implements IPrintPpService {

	@Resource
	private PrintDaoMapper dao;
	
	@Override
	public List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh) {
		
		return dao.getPrintPpList(beginDate, endDate, kpdq, zddh);
	}

}
