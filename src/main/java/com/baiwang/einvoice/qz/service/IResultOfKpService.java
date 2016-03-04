package com.baiwang.einvoice.qz.service;

import com.baiwang.einvoice.qz.beans.ResultOfKp;

public interface IResultOfKpService {

	ResultOfKp queryResult(String ddhm);

	int save(ResultOfKp result);

}
