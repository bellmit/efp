package com.baiwang.einvoice.qz.service;

import java.util.List;
import java.util.Map;

public interface IPrintPpService {

	List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh, String fplx);

	List<Map<String, String>> getPrintPpsList(String beginDate, String endDate, String beginfphm, String endfphm, String fplx);

	List<Map<String, String>> showDetail(String begin, String end, String fplx);

}
