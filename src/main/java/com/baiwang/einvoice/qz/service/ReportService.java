/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service;

import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.beans.ReportTotal;

/**
  * @ClassName: ReportDetailService
  * @Description: TODO
  * @author Administrator
  * @date 2016年3月3日 下午8:21:46
  */
public interface ReportService {
	ReportDetail getFpByLSH(String fpqqlsh);
	
	ReportTotal getFpStatByLSH(String fpqqlsh);
	
	List<ReportDetail> getFpListByCondition(Map<String, Object> condition);
	
	int getFpCount(Map<String, Object> condition);
	int getFpStatCount(Map<String, Object> condition);
	
	List<ReportTotal> getFpStatListByCondition(Map<String, Object> condition);
	
	List<ReportDetail> getFpListByCondition4d(Map<String, Object> condition);
	
	List<ReportTotal> getFpListByCondition4e(Map<String, Object> condition);
}
