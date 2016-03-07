package com.baiwang.einvoice.qz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PrintDaoMapper {

	List<Map<String, String>> getPrintPpList(@Param("beginDate")String beginDate, 
			@Param("endDate")String endDate, @Param("kpdq")String kpdq, @Param("zddh")String zddh);

}
