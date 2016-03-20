/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiwang.einvoice.qz.service.IDocumentService;
import com.baiwang.einvoice.qz.utils.ConfigUtil;

/**
  * @ClassName: DocumentServiceImpl
  * @Description: 版式文件查询
  * @author zhaowei
  * @date 2016年3月19日 下午8:26:11
  */
@Service("documentService")
public class DocumentServiceImpl implements IDocumentService {

	private static final Log logger = LogFactory.getLog(DocumentServiceImpl.class);
	
	private static final String document_url = ConfigUtil.get("document.ts.url");
	
	private static final String token_ts_url = ConfigUtil.get("token.ts.url");
	
	private static final String token_ts_username = ConfigUtil.get("token.ts.username");
	
	private static final String token_ts_password = ConfigUtil.get("token.ts.password");
	
	private static final String token_ts_grant_type = ConfigUtil.get("token.ts.grant_type");
	
	private static final String token_ts_client_id = ConfigUtil.get("token.ts.client_id");
	
	private static final String token_ts_client_secret = ConfigUtil.get("token.ts.client_secret");
	/**
	  * <p>Title: query</p>
	  * <p>Description: </p>
	  * @param fphm
	  * @param fpdm
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IDocumentService#query(java.lang.String, java.lang.String)
	  */
	@Override
	public String query(String fphm, String fpdm) {
		

		StringBuffer sb = new StringBuffer();
		String returncode = null;
		
		String url  = document_url.replace("{FPHM}",fphm).replace("{FPDM}", fpdm);
		CloseableHttpClient hc = HttpClients.createDefault();
		HttpGet hg = new HttpGet(url);
		
		
		try {
			//获取TS TOKEN值
			String tokenJson = getToken();
			//String tokenJson = "{\"token_type\":\"bearer\",\"access_token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiYWNjZXNzVG9rZW4iLCJ1c2VySWQiOiI2NjhhMmQ3OC1kOGE0LTRmNTYtODljMi04NDAwNGRhNzliOWQiLCJyb2xlIjoiY29tcGFueSIsImNsaWVudElkIjoiYnd0c19jbGllbnRfdGVzdCIsInRlbmFudElkIjoiYTg1ZGI4ZmUtMzNkYS00NWJkLWJjZWEtMTlhNTFlODQyY2IzIiwiZXhwaXJlVGltZSI6MTQ1ODM2MTAyNTY0OSwiaWF0IjoxNDU4MzU3NDI1fQ.2WV90aXkMrlURZj8lAfuIk2hbkHCCjuPJ7zf6qXYEok\",\"expires_in\":3600,\"refresh_token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaFRva2VuIiwidXNlcklkIjoiNjY4YTJkNzgtZDhhNC00ZjU2LTg5YzItODQwMDRkYTc5YjlkIiwicm9sZSI6ImNvbXBhbnkiLCJjbGllbnRJZCI6ImJ3dHNfY2xpZW50X3Rlc3QiLCJ0ZW5hbnRJZCI6ImE4NWRiOGZlLTMzZGEtNDViZC1iY2VhLTE5YTUxZTg0MmNiMyIsImV4cGlyZVRpbWUiOjE0NTg5NjIyMjU2NTUsImlhdCI6MTQ1ODM1NzQyNX0.ay2g64CKBWTgM2RHq3CLeuTIYvA52d_TNCbI8eQUVvc\"}";
			JSONObject jsonObject =JSON.parseObject(tokenJson);
			System.out.println(jsonObject.get("access_token"));
			//请求头增加Authorization
			hg.setHeader("Authorization", "{"+jsonObject.get("access_token")+"}");
			//hg.setHeader("Authorization", "bearer{eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiYWNjZXNzVG9rZW4iLCJ1c2VySWQiOiI2NjhhMmQ3OC1kOGE0LTRmNTYtODljMi04NDAwNGRhNzliOWQiLCJyb2xlIjoiY29tcGFueSIsImNsaWVudElkIjoiYnd0c19jbGllbnRfdGVzdCIsInRlbmFudElkIjoiYTg1ZGI4ZmUtMzNkYS00NWJkLWJjZWEtMTlhNTFlODQyY2IzIiwiZXhwaXJlVGltZSI6MTQ1ODM1ODA2MDEyNSwiaWF0IjoxNDU4MzU0NDYwfQ.9gAhNkStgeXmIXxNIITGr4ZUEWf17L_3kNMcvsnePFk}");
			HttpResponse response = hc.execute(hg);
			HttpEntity entity = response.getEntity();
			byte[] fileContent = null;
			InputStream htm_in = null;
			String fileName = null;
			if(response.getStatusLine().getStatusCode() == 200){
				
				if (entity != null) {
					htm_in = entity.getContent();
					//Header status = response.getFirstHeader("Status");
					Header filename = response.getFirstHeader("Content-Disposition");
					String attachment = filename.getValue();
					if(attachment!=null || "".equals(attachment)){
						returncode = "0";
						fileName = attachment.substring(attachment. indexOf("=")+1);
						fileContent = InputStream2ByteArray(htm_in, "reports" ,attachment.substring(attachment. indexOf("=")+1) );
					}
					
				}
			}
			
			if(!StringUtils.isEmpty(returncode)){
				sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
				sb.append("<rtnmsg>");
				sb.append("<returncode>"+returncode+"</returncode>");
				sb.append("<returnmsg>查询成功</returnmsg>");
				sb.append("<returndata>");
				sb.append("<fpdm>"+fpdm+"</fpdm>");
				sb.append("<fphm>"+fphm+"</fphm>");
				sb.append("<bswj>");
				sb.append("<wjlx>"+fileName+"</wjlx>");
				sb.append("<wjnr>"+new Base64().encodeAsString(fileContent)+"</wjnr>");
				sb.append("<yswjcd>"+fileContent.length+"<yswjcd>");
				sb.append("</bswj>");
				sb.append("</returndata>");
				sb.append("</rtnmsg>");
				logger.info("版式文件查询成功！");
			}else{
				sb.append("版式文件查询失败！请检查发票代码和发票号码是否正确！");
				logger.info("版式文件查询失败！");
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
			
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return sb.toString();

	}
	
	public static byte[] InputStream2ByteArray(InputStream in, String filePath ,String name) {
		ByteArrayOutputStream fos = null;
		//String fileName = filePath + File.separator + name;
		try {
			int flag = 0;
			fos = new ByteArrayOutputStream(in.available());
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

		return fos.toByteArray();
	}

	public static String getToken() throws UnsupportedEncodingException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();// 参数列表
		params.add(new BasicNameValuePair("username", token_ts_username));
		params.add(new BasicNameValuePair("password", token_ts_password));
		params.add(new BasicNameValuePair("grant_type", token_ts_grant_type));
		params.add(new BasicNameValuePair("client_id", token_ts_client_id));
		params.add(new BasicNameValuePair("client_secret", token_ts_client_secret));
		HttpPost hp = new HttpPost(token_ts_url);
		CloseableHttpClient client = HttpClients.createDefault();
		//header必须项
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

}
