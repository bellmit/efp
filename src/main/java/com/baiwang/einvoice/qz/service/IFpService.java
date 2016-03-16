/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.OrderDetail;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
  * @ClassName: FpService
  * @Description: TODO
  * @author zhaowei
  * @date 2016年3月3日 上午9:18:31
  */
public interface IFpService {

	
	void saveInfo(OrderDetail orderDetail, Kpxx kpxx, List<Fpmx> fpmxList , String fpqqlsh);
	
	List<Map<String, String>> getPlainList(HashMap<String, String> param);
	
	List<Map<String, String>> getSpecialList(HashMap<String, String> param);
	
	Kpxx getKpxxByFpqqlsh(String fpqqlsh);
	
	List<Fpmx> getFpmxByFpqqlsh(String fpqqlsh);
	
	void updateFpztByFpqqlsh(String fpqqlsh);
	
	int zfByFpqqlsh(String fpqqlsh);
	
	String getXml(Kpxx kpxx , List<Fpmx> fpmxList);
	
	List<HashMap<String, Object>> listPlain(HashMap<String, String> param,int pageIndex,int pageSize);
	
	List<HashMap<String, Object>> listSpecial(HashMap<String, String> param,int pageIndex,int pageSize);
	
	List<Map<String, Object>> getPlainList4zf(Map<String, Object> param);
    
	List<Map<String, Object>> getSpecialList4zf(Map<String, Object> param);
	
	int getPlainList4zfCount(Map<String, Object> param);
    
    int getSpecialList4zfCount(Map<String, Object> param);
	
	void saveCallBackInfo(Kpxx kpxx);
}
