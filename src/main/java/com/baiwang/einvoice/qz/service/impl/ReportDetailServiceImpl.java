/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.dao.ReportDetailMapper;
import com.baiwang.einvoice.qz.service.ReportDetailService;

/**
  * @ClassName: ReportDetailServiceImpl
  * @Description: TODO
  * @author Administrator
  * @date 2016年3月3日 下午8:25:17
  */
@Service
public class ReportDetailServiceImpl implements ReportDetailService {
	@Resource
	private ReportDetailMapper reportDao;

	@Override
	public List<ReportDetail> getFpListByCondition(Map<String, Object> condition) {
		
		return reportDao.getFpListByCondition(condition);
		
	}

	@Override
	public ReportDetail getFpByLSH(String fpqqlsh) {
		return reportDao.getFpByLSH(fpqqlsh);
	}

}
