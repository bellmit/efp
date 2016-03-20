package com.baiwang.einvoice.qz.service.skService;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.utils.ConfigUtil;
import com.baiwang.einvoice.qz.utils.token.TokenHelper;

@Service
public class TsPlatService {

	private static final Log logger = LogFactory.getLog(TsPlatService.class);
	
//	private String tsUrl = "http://192.168.6.12:19922/external/documents";
	private String tsUrl = ConfigUtil.get("request.ts.url");
	
	//供应商税控编号，需要与UBL中的 <cac:AccountingSupplierParty><cbc:CustomerAssignedAccountID> 一致
    private static final String supplierTaxNumber = "0000000001000032";

    //该公司持有的token_key
    private static final String tokenKey = "OMJXJ9YYALR4YX0M";

    private static final String tokenValue = TokenHelper.generateToken(supplierTaxNumber, tokenKey);
	
	public String reqestTsPlat(String ubl){
		logger.info("////////////////请求ts发票平台接收ubl//////////////" + ubl);
		BufferedReader in = null;
		String success = "";
		try{
			URL url = new URL(tsUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			/*conn.setRequestProperty("BWTS-TOKEN", "{"+tokenValue+"}");*/
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			
			conn.setConnectTimeout(300000);  
			conn.setReadTimeout(30000); 
			
			conn.setRequestProperty("Content-Length", "" + ubl.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(ubl);
			out.flush();
			out.close();
			logger.info("////////请求ts发票平台//////responseCode///////" + conn.getResponseCode());
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() < 300){
				success = "0000";
			}else{
				success = "4000";
			}
		}catch(Exception e){
			success = "4000";
			logger.error("////////////////请求ts发票平台///////请求异常//////////////////");
			e.printStackTrace();
		}finally{
			try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		
		
		return success;
	}
	
	
}
