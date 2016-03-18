package com.baiwang.einvoice.qz.service;

import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.PrintConfig;
import com.baiwang.einvoice.qz.beans.SkConfig;

public interface IPrintPpService {

	List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String hyid, String fphm, String zddh, String gmfsjh, String fplx, int requestPage, int pageSize, String xsfnsrsbh);
	int queryCount(String beginDate, String endDate, String hyid, String fphm, String zddh, String gmfsjh, String fplx, int requestPage, int pageSize, String xsfnsrsbh);

	List<Map<String, String>> getPrintPpsList(String beginDate, String endDate, String beginfphm, String endfphm, String fplx, String xsfnsrsbh);

	List<Map<String, String>> showDetail(String begin, String end, String fplx, int requestPage, int pageSize);

	SkConfig getSkParameter(String xsfnsrsbh);

	PrintConfig getPrintParameter(String fplx);

	int savePrintResult(String fpqqlsh, String fpzt);

	List<Map<String, String>> getPrintsFphm(String beginfphm, String endfphm);


	int queryDetailCount(String begin, String end, String fplx);

}
