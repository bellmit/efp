package com.baiwang.einvoice.qz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.PrintConfig;
import com.baiwang.einvoice.qz.beans.SkConfig;
import com.baiwang.einvoice.qz.dao.PrintConfigMapper;
import com.baiwang.einvoice.qz.dao.SkConfigMapper;
import com.baiwang.einvoice.qz.service.ISetService;

@Service
public class SetServiceImpl implements ISetService {

	@Resource
	private SkConfigMapper skdao;
	
	@Resource
	private PrintConfigMapper dydao;
	
	/*@Resource
	private SysconfigMapper dao;*/

	@Override
	public int saveSksetting(SkConfig skconfig) {
		SkConfig sk = skdao.querySksetting(skconfig.getKpdq());
		if(null != sk){
			skconfig.setId(sk.getId());
			return skdao.updateByPrimaryKey(skconfig);
		}else{
			return skdao.saveSksetting(skconfig);
		}
		
	}

	@Override
	public Integer savePrintsetting(PrintConfig printconfig) {
		PrintConfig dy = dydao.queryPrintsetting(printconfig.getFplx());
		if(null != dy){
			printconfig.setId(dy.getId());
			return dydao.updateByPrimaryKey(printconfig);
		}else{
			return dydao.savePrintsetting(printconfig);
		}
	}

	@Override
	public SkConfig initsetting(String czydm) {
		
		return skdao.initsetting(czydm);
	}
	
	

}
