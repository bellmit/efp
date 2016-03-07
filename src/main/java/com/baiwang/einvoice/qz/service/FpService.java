/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.CustomOrder;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;

/**
  * @ClassName: FpService
  * @Description: TODO
  * @author zhaowei
  * @date 2016年3月3日 上午9:18:31
  */
public interface FpService {

	void saveXmlInfo(Business business);
	
	void saveInfo(CustomOrder customOrder, Kpxx kpxx, List<Fpmx> fpmxList);
	
	List<Map<String, String>> getPlainList(HashMap<String, String> param);
	
	List<Map<String, String>> getSpecialList(HashMap<String, String> param);
	
	Kpxx getKpxxByFpqqlsh(String fpqqlsh);
	
	List<Fpmx> getFpmxByFpqqlsh(String fpqqlsh);
	
	void updateFpztByFpqqlsh(String fpqqlsh);
	
	String getXml(Kpxx kpxx , List<Fpmx> fpmxList);
}
