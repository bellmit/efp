package com.baiwang.einvoice.qz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.dao.PrintDaoMapper;
import com.baiwang.einvoice.qz.service.IPrintPpService;

@Service
public class PrintPpServiceImpl implements IPrintPpService {

	@Resource
	private PrintDaoMapper dao;
	
	@Override
	public List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh, String fplx) {
		
		return dao.getPrintPpList(beginDate, endDate, kpdq, zddh, fplx);
	}

	@Override
	public List<Map<String, String>> getPrintPpsList(String beginDate, String endDate, String beginfphm,
			String endfphm, String fplx) {
		
		List<Map<String, String>> _list = new ArrayList<Map<String, String>>();
		
		List<Map<String, String>> list = dao.getPrintPpsList(beginDate, endDate, beginfphm, endfphm, fplx);
		if(null != list && list.size() > 0){
			
			String _beginDate = list.get(0).get("kprq");
			String _endDate = list.get(0).get("kprq");
			int _beginfphm = Integer.valueOf(list.get(0).get("fphm"));
			int _endfphm = _beginfphm;
			System.out.println("第一个发票号码：" + _beginfphm);
			
			int _beforefphm = _endfphm;
			
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
						_list.add(_map);
					}
				}else{
					_map.put("beginfphm", Integer.toString(_beginfphm));
					_map.put("endfphm", Integer.toString(_endfphm));
					_map.put("beginDate", _beginDate);
					_map.put("endDate", _endDate);
					_list.add(_map);
					
					_beginfphm = fphm;
//					_endfphm = fphm;
					_beginDate = list.get(i).get("kprq");
//					_endDate = list.get(i).get("kprq");
				}
				_beforefphm = fphm;
				
			}
		}
		
		
		return _list;
	}

	@Override
	public List<Map<String, String>> showDetail(String begin, String end, String fplx) {
		
		return dao.showDetail(begin, end, fplx);
	}

}
