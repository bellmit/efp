package com.baiwang.einvoice.qz.service;

import java.util.Map;

import com.baiwang.einvoice.qz.beans.ResultOfKp;

public interface IResultOfKpService {

	ResultOfKp queryResult(String ddhm, String corre);

	int save(ResultOfKp result);

	Map<String, String> queryResult(String zddh, String fddh, String fplx);

}
