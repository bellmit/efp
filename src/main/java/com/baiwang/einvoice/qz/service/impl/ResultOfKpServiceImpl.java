package com.baiwang.einvoice.qz.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.ResultOfKp;
import com.baiwang.einvoice.qz.controller.FpController;
import com.baiwang.einvoice.qz.dao.ResultOfKpMapper;
import com.baiwang.einvoice.qz.service.IResultOfKpService;

@Service
public class ResultOfKpServiceImpl implements IResultOfKpService {
	
	private static final Log logger = LogFactory.getLog(ResultOfKpServiceImpl.class);
	
	@Resource
	private ResultOfKpMapper dao;

	@Override
	public ResultOfKp queryResult(String ddhm, String correlationId) {
		
		return dao.queryResult2(ddhm,correlationId);
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

	/**
	 * 进入前置先根据两个订单号、发票类型查询结果
	 */
	public Map<String, String> queryResult(String zddh, String fddh, String fplx) {
		logger.info("** 订单查询   **主订单号：" + zddh +",副订单号：" + fddh + ",发票类型：" + fplx);
		Map<String, String> map = new HashMap<String, String>();
		Kpxx kpxx = dao.queryResult(zddh, fddh, fplx);
		
		if(null == kpxx){
			logger.info("** 订单查询   **主订单号：" + zddh +",副订单号：" + fddh + ",发票类型：" + fplx + "查询为null");
			return null;
		}else if("026".equals(kpxx.getFplx())){//电子票
			if("1".equals(kpxx.getFpzt())){
				logger.info("** 订单查询   **主订单号：" + zddh +",副订单号：" + fddh + ",发票类型：" + fplx + "查询为fpzt:1");
				map.put("returnCode", "2000");
				map.put("returnMsg", "电子开票正在处理中");
				return map;
			}else{
				logger.info("** 订单查询   **主订单号：" + zddh +",副订单号：" + fddh + ",发票类型：" + fplx + ",开具成功，查询为fpzt:" + kpxx.getFpzt());
				map.put("returnCode", "0000");
				map.put("returnMsg", "电子开票成功");
				return map;
			}
		}else{//纸质发票
			if("1".equals(kpxx.getFpzt())){
				logger.info("** 订单查询   **主订单号：" + zddh +",副订单号：" + fddh + ",发票类型：" + fplx + "查询为fpzt:1");
				map.put("returnCode", "2000");
				map.put("returnMsg", "待开票");
				return map;
			}else if("2".equals(kpxx.getFpzt())){
				logger.info("** 订单查询   **主订单号：" + zddh +",副订单号：" + fddh + ",发票类型：" + fplx + "查询为fpzt:2");
				map.put("returnCode", "2000");
				map.put("returnMsg", "待打印");
				return map;
			}else{
				logger.info("** 订单查询   **主订单号：" + zddh +",副订单号：" + fddh + ",发票类型：" + fplx + ",开具成功，查询为fpzt:" + kpxx.getFpzt());
				map.put("returnCode", "0000");
				map.put("returnMsg", "开票成功");
				return map;
			}
		}
		
	}

}
