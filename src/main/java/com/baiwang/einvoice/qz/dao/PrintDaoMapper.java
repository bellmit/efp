package com.baiwang.einvoice.qz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PrintDaoMapper {

	List<Map<String, String>> getPrintPpList(@Param("beginDate")String beginDate, 
			@Param("endDate")String endDate, @Param("hyid")String hyid, @Param("fphm")String fphm, @Param("zddh")String zddh, @Param("shrdh")String shrdh,
			@Param("fplx")String fplx, @Param("requestPage")int requestPage, @Param("pageSize")int pageSize, @Param("xsfnsrsbh")String xsfnsrsbh);
	
	List<Integer> queryCount(@Param("beginDate")String beginDate, 
			@Param("endDate")String endDate, @Param("hyid")String hyid, @Param("fphm")String fphm, @Param("zddh")String zddh, @Param("shrdh")String shrdh,
			@Param("fplx")String fplx, @Param("requestPage")int requestPage, @Param("pageSize")int pageSize, @Param("xsfnsrsbh")String xsfnsrsbh);

	List<Map<String, Object>> getPrintPpsList(@Param("beginDate")String beginDate, @Param("endDate")String endDate, 
			@Param("beginfphm")String beginfphm, @Param("endfphm")String endfphm, @Param("fplx")String fplx, @Param("xsfnsrsbh")String xsfnsrsbh);

	List<Map<String, String>> showDetail(@Param("begin")String begin, @Param("end")String end, @Param("fplx")String fplx,
			@Param("requestPage")int requestPage, @Param("pageSize")int pageSize, @Param("xsfnsrsbh")String xsfnsrsbh);

	List<Integer> queryDetailCount(@Param("begin")String begin, @Param("end")String end, @Param("fplx")String fplx, @Param("xsfnsrsbh")String xsfnsrsbh);
	
	int savePrintResult(@Param("fpqqlsh")String fpqqlsh, @Param("fpzt")String fpzt);

	List<Map<String, String>> getPrintsFphm(@Param("beginfphm")String beginfphm, @Param("endfphm")String endfphm);



}
