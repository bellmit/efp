package com.baiwang.einvoice.qz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.PrintConfig;
import com.baiwang.einvoice.qz.beans.SkConfig;
import com.baiwang.einvoice.qz.dao.PrintConfigMapper;
import com.baiwang.einvoice.qz.dao.PrintDaoMapper;
import com.baiwang.einvoice.qz.dao.SkConfigMapper;
import com.baiwang.einvoice.qz.service.IPrintPpService;

@Service
public class PrintPpServiceImpl implements IPrintPpService {

	@Resource
	private PrintDaoMapper dao;
	
	@Resource
	private SkConfigMapper skdao;
	
	@Resource
	private PrintConfigMapper dydao;
	
	@Override
	public List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh, String fplx, int requestPage, int pageSize, String xsfnsrsbh) {
		requestPage = (requestPage - 1) * pageSize;
		return dao.getPrintPpList(beginDate, endDate, kpdq, zddh, fplx, requestPage, pageSize, xsfnsrsbh);
	}
	
	@Override
	public int queryCount(String beginDate, String endDate, String kpdq, String zddh, String fplx, String xsfnsrsbh) {
		
		return dao.queryCount(beginDate, endDate, kpdq, zddh, fplx, xsfnsrsbh);
	}

	@Override
	public List<Map<String, String>> getPrintPpsList(String beginDate, String endDate, String beginfphm,
			String endfphm, String fplx, String xsfnsrsbh) {
		
		List<Map<String, String>> _list = new ArrayList<Map<String, String>>();
		
		List<Map<String, String>> list = dao.getPrintPpsList(beginDate, endDate, beginfphm, endfphm, fplx, xsfnsrsbh);
		if(null != list && list.size() > 0){
			
			String _beginDate = list.get(0).get("kprq");
			String _endDate = list.get(0).get("kprq");
			int _beginfphm = Integer.valueOf(list.get(0).get("fphm"));
			int _endfphm = _beginfphm;
			System.out.println("第一个发票号码：" + _beginfphm);
			
			int _beforefphm = _endfphm;
			if(list.size() == 1){
				Map<String, String> _map = new HashMap<String, String>();
				_map.put("beginfphm", Integer.toString(_beginfphm));
				_map.put("endfphm", Integer.toString(_endfphm));
				_map.put("beginDate", _beginDate);
				_map.put("endDate", _endDate);
				_map.put("lxzs", Integer.toString(_endfphm - _beginfphm + 1));
				_list.add(_map);
			}
			
			for(int i = 1; i < list.size(); i++){
				Map<String, String> _map = new HashMap<String, String>();
				
				Map<String, String> map = list.get(i);
				
				int fphm = Integer.valueOf(map.get("fphm"));
				System.out.println("第" + i + "个发票号码：" + fphm);
				
				
				if(fphm == (_beforefphm + 1)){
					_endfphm = fphm;
					_endDate = list.get(i).get("kprq");
					
					if(i == (list.size() - 1)){//是否最后一个
						_map.put("beginfphm", Integer.toString(_beginfphm));
						_map.put("endfphm", Integer.toString(_endfphm));
						_map.put("beginDate", _beginDate);
						_map.put("endDate", _endDate);
						_map.put("lxzs", Integer.toString(_endfphm - _beginfphm + 1));
						_list.add(_map);
					}
				}else{
					_map.put("beginfphm", Integer.toString(_beginfphm));
					_map.put("endfphm", Integer.toString(_endfphm));
					_map.put("beginDate", _beginDate);
					_map.put("endDate", _endDate);
					_map.put("lxzs", Integer.toString(_endfphm - _beginfphm + 1));
					_list.add(_map);
					
					_beginfphm = fphm;
					_endfphm = fphm;
					_beginDate = list.get(i).get("kprq");
					_endDate = list.get(i).get("kprq");
					if(i == (list.size() - 1)){//是否最后一个
						Map<String, String> _map_ = new HashMap<String, String>();
						_map_.put("beginfphm", Integer.toString(_beginfphm));
						_map_.put("endfphm", Integer.toString(_beginfphm));
						_map_.put("beginDate", _beginDate);
						_map_.put("endDate", _beginDate);
						_map_.put("lxzs", "1");
						_list.add(_map_);
					}
				}
				_beforefphm = fphm;
				
			}
		}
		
		
		return _list;
	}

	@Override
	public List<Map<String, String>> showDetail(String begin, String end, String fplx, int requestPage, int pageSize) {
		requestPage = (requestPage - 1) * pageSize;
		return dao.showDetail(begin, end, fplx, requestPage, pageSize);
	}

	@Override
	public int queryDetailCount(String begin, String end, String fplx) {
		
		return dao.queryDetailCount(begin, end, fplx);
	}
	
	@Override
	public SkConfig getSkParameter(String userId) {
		
		return skdao.initsetting(userId);
	}

	@Override
	public PrintConfig getPrintParameter(String fplx) {
		
		return dydao.queryPrintsetting( fplx);
	}

	@Override
	public int savePrintResult(String fpqqlsh, String fpzt) {
		
		return dao.savePrintResult( fpqqlsh,  fpzt);
	}

	@Override
	public List<Map<String, String>> getPrintsFphm(String beginfphm, String endfphm) {
		
		return dao.getPrintsFphm( beginfphm,  endfphm);
	}

}
