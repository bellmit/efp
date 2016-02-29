package com.baiwang.einvoice.service.skService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class TsPlatService {

	private static final Log logger = LogFactory.getLog(TsPlatService.class);
	
	private String tsUrl = "http://192.168.6.12:19922/external/documents";
	
	public String reqestTsPlat(String ubl){
		logger.info("////////////////请求ts发票平台接收ubl//////////////" + ubl);
		BufferedReader in = null;
		String success = "0000";
		try{
			URL url = new URL(tsUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			
			conn.setConnectTimeout(300000);  
			conn.setReadTimeout(30000); 
			
			conn.setRequestProperty("Content-Length", "" + ubl.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(ubl);
			out.flush();
			out.close();
			logger.error("////////请求ts发票平台//////responseCode///////" + conn.getResponseCode());
			if (conn.getResponseCode() == 200){
				String line ="";
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				while ((line = in.readLine()) != null) {
					success += line;
				}
//				return success;
			}else if(conn.getResponseCode() > 200 && conn.getResponseCode() < 300){
//				return InvoiceUtil.backMsg("0000", "开具成功。", xml);
			}else{
//				return InvoiceUtil.backMsg(Integer.toString(conn.getResponseCode()), "开具失败。", xml);
				success = "4000";
//				return "4000";
			}
		}catch(Exception e){
			logger.error("////////////////请求ts发票平台///////请求异常//////////////////");
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
