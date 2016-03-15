/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.beans.ReportTotal;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
  * @ClassName: ReportDetailService
  * @Description: TODO
  * @author Administrator
  * @date 2016年3月3日 下午8:21:46
  */
public interface ReportService {
	ReportDetail getFpByLSH(String fpqqlsh);
	
	ReportTotal getFpStatByLSH(String fpqqlsh);
	
	PageList<HashMap<String,Object>> getFpListByCondition(Map<String, Object> condition);
	
	PageList<HashMap<String,Object>> getFpStatListByCondition(Map<String, Object> condition);
	
	List<ReportDetail> getFpListByCondition4d(Map<String, Object> condition);
	
	List<ReportTotal> getFpListByCondition4e(Map<String, Object> condition);
}
