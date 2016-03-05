package com.baiwang.einvoice.qz.service.impl;

import java.util.Map;

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
	public ResultOfKp queryResult(String ddhm, String correlationId) {
		
		return dao.queryResult(ddhm,correlationId);
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

	@Override
	public Map<String, String> queryResult(String zddh, String fddh, String fplx) {
		// TODO Auto-generated method stub
		return null;
	}

}
