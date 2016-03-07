package com.baiwang.einvoice.qz.service;

import java.util.List;
import java.util.Map;

public interface IPrintPpService {

	List<Map<String,String>> getPrintPpList(String beginDate, String endDate, String kpdq, String zddh);

}
