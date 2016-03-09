package com.baiwang.einvoice.qz.service;

import java.util.Map;

public interface IResultOfSkService {
	public void saveResultOfSk(Map<String, String> map);
	
	public Map<String, String> queryResult(String zddh, String fddh, String fplx);

}
