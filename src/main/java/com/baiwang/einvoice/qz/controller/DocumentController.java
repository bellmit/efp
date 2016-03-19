/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baiwang.einvoice.qz.utils.ConfigUtil;
import com.baiwang.einvoice.qz.utils.InvoiceUtil;

/**
 * @ClassName: DocumentController
 * @Description: 发票文件查询接口
 * @author zhaowei
 * @date 2016年3月19日 上午8:59:08
 */
@Controller
@RequestMapping("/document")
public class DocumentController {

	private static final Log logger = LogFactory.getLog(DocumentController.class);

	private static final String document_url = ConfigUtil.get("document.ts.url");

	@RequestMapping("/query")
	public static void queryDocument(String xml) throws ClientProtocolException, IOException {

		logger.info("接收到查询报文:" + xml);
		// String returncode = null;
		String fphm = null;
		String fpdm = null;
		if (StringUtils.isEmpty(xml)) {
			/*fphm = InvoiceUtil.getIntervalValue(xml, "<FPHM>", "</FPHM>");
			fpdm = InvoiceUtil.getIntervalValue(xml, "<FPDM>", "</FPDM>");*/
			
			String url  = document_url.replace("{FPHM}", "111001571071").replace("{FPDM}", "1231512516");
			CloseableHttpClient hc = HttpClients.createDefault();
			HttpGet hg = new HttpGet(url);
			
			String tokenJson = getToken();
			//String tokenJson = "{\"token_type\":\"bearer\",\"access_token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiYWNjZXNzVG9rZW4iLCJ1c2VySWQiOiI2NjhhMmQ3OC1kOGE0LTRmNTYtODljMi04NDAwNGRhNzliOWQiLCJyb2xlIjoiY29tcGFueSIsImNsaWVudElkIjoiYnd0c19jbGllbnRfdGVzdCIsInRlbmFudElkIjoiYTg1ZGI4ZmUtMzNkYS00NWJkLWJjZWEtMTlhNTFlODQyY2IzIiwiZXhwaXJlVGltZSI6MTQ1ODM2MTAyNTY0OSwiaWF0IjoxNDU4MzU3NDI1fQ.2WV90aXkMrlURZj8lAfuIk2hbkHCCjuPJ7zf6qXYEok\",\"expires_in\":3600,\"refresh_token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaFRva2VuIiwidXNlcklkIjoiNjY4YTJkNzgtZDhhNC00ZjU2LTg5YzItODQwMDRkYTc5YjlkIiwicm9sZSI6ImNvbXBhbnkiLCJjbGllbnRJZCI6ImJ3dHNfY2xpZW50X3Rlc3QiLCJ0ZW5hbnRJZCI6ImE4NWRiOGZlLTMzZGEtNDViZC1iY2VhLTE5YTUxZTg0MmNiMyIsImV4cGlyZVRpbWUiOjE0NTg5NjIyMjU2NTUsImlhdCI6MTQ1ODM1NzQyNX0.ay2g64CKBWTgM2RHq3CLeuTIYvA52d_TNCbI8eQUVvc\"}";
			JSONObject jsonObject =JSON.parseObject(tokenJson);
			System.out.println(jsonObject.get("access_token"));
			hg.setHeader("Authorization", "{"+jsonObject.get("access_token")+"}");
			//hg.setHeader("Authorization", "bearer{eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiYWNjZXNzVG9rZW4iLCJ1c2VySWQiOiI2NjhhMmQ3OC1kOGE0LTRmNTYtODljMi04NDAwNGRhNzliOWQiLCJyb2xlIjoiY29tcGFueSIsImNsaWVudElkIjoiYnd0c19jbGllbnRfdGVzdCIsInRlbmFudElkIjoiYTg1ZGI4ZmUtMzNkYS00NWJkLWJjZWEtMTlhNTFlODQyY2IzIiwiZXhwaXJlVGltZSI6MTQ1ODM1ODA2MDEyNSwiaWF0IjoxNDU4MzU0NDYwfQ.9gAhNkStgeXmIXxNIITGr4ZUEWf17L_3kNMcvsnePFk}");
			HttpResponse response = hc.execute(hg);
			HttpEntity entity = response.getEntity();
			String htm_str = null;
			InputStream htm_in = null;

			if (entity != null) {
				htm_in = entity.getContent();
				//Header status = response.getFirstHeader("Status");
				Header filename = response.getFirstHeader("Content-Disposition");
				String attachment = filename.getValue();
				if(attachment!=null || "".equals(attachment)){
					htm_str = InputStream2File(htm_in, "reports" , attachment.substring(attachment.indexOf("=")+1) );
					System.out.println(htm_str);
				}
				//System.out.println(status.getValue());
				System.out.println(filename.getValue());
				
			}
		}

	}

	public static String InputStream2File(InputStream in, String filePath ,String name) {
		FileOutputStream fos = null;
		String fileName = filePath + File.separator + name;
		try {
			int flag = 0;
			fos = new FileOutputStream(fileName);
			byte[] b = new byte[1024];
			while ((flag = in.read(b, 0, 1024)) != -1) {
				fos.write(b, 0, flag);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fos.close();
			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

		return fileName;
	}

	public static String getToken() throws UnsupportedEncodingException {
		String url = "http://58.211.225.90:7788/oauth/token";
		List<NameValuePair> params = new ArrayList<NameValuePair>();// 参数列表
		params.add(new BasicNameValuePair("username", "tch+integration@tradeshift.com"));
		params.add(new BasicNameValuePair("password", "Y2GN2uni"));
		params.add(new BasicNameValuePair("grant_type", "password"));
		params.add(new BasicNameValuePair("client_id", "bwts_client_test"));
		params.add(new BasicNameValuePair("client_secret", "SnB7KVkq"));
		HttpPost hp = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		hp.setHeader("Content-Type", "application/x-www-form-urlencoded");
		UrlEncodedFormEntity uparams = new UrlEncodedFormEntity(params, "UTF-8");
		hp.setEntity(uparams);
		String token_json = null;
		try {

			HttpResponse response = client.execute(hp);

			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {

				InputStream is = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuilder buffer = new StringBuilder();
				try {
					String line;
					while ((line = reader.readLine()) != null) {
						buffer.append(line);
					}
				} finally {
					reader.close();
				}
				token_json = buffer.toString();
				System.out.println(token_json);

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token_json;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//getToken();
		queryDocument(null);
	}
}
