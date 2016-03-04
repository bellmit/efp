package com.baiwang.einvoice.qz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.ResultOfKp;
import com.baiwang.einvoice.qz.dao.ResultOfKpMapper;
import com.baiwang.einvoice.qz.service.IResultOfKpService;

@Service
public class ResultOfKpServiceImpl implements IResultOfKpService {
	
	@Resource
	private ResultOfKpMapper dao;

	@Override
	public ResultOfKp queryResult(String ddhm) {
		
		return dao.queryResult(ddhm);
	}

	@Override
	public int save(ResultOfKp result) {
		
		int count = dao.selectByDdhm(result.getDdhm());
		System.out.println("-----=====" + count);
		if(count == 0){
			count = dao.save(result);
		}else{
			count = dao.updateByDdhm(result);
		}
		
		return count;
	}

}
