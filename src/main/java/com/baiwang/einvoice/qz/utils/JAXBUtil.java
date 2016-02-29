package com.baiwang.einvoice.qz.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.baiwang.einvoice.qz.beans.Business;



public class JAXBUtil{

	private static ThreadLocal<Unmarshaller> obj = new ThreadLocal<Unmarshaller>(){

		@Override
		protected Unmarshaller initialValue() {
			Unmarshaller unmarshaller = null;
            try {
            	JAXBContext context = JAXBContext.newInstance(Business.class);  
            	unmarshaller = context.createUnmarshaller();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return unmarshaller;
			
		}
		
	};
	/**
	  * @author zhaowei
	  * @Description: TODO
	  * @param @param data
	  * @param @return  
	  * @return Fp  
	  * @throws
	  * @date 2016年2月29日 上午11:06:37
	 */
	public static Business unmarshallObject(byte[] data) {

		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		Unmarshaller unmarshaller = obj.get();
		if (unmarshaller != null) {
			try {
				return (Business) unmarshaller.unmarshal(new StreamSource(bais));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String xml = "<?xml version=\"1.0\" encoding=\"gbk\"?><business id=\"FPKJ\" comment=\"发票开具\"><REQUEST_COMMON_FPKJ class=\"REQUEST_COMMON_FPKJ\"><COMMON_FPKJ_FPT class=\"COMMON_FPKJ_FPT\"><FPQQLSH>18256001466415008145</FPQQLSH><XTBS>erp</XTBS><JHKEY>0001111000111</JHKEY><KPLX>0</KPLX><XSF_NSRSBH>110109004357777777</XSF_NSRSBH><XSF_MC>百旺亿城测试1</XSF_MC><XSF_DZDH>11012345678</XSF_DZDH><XSF_YHZH>6666666666666666666</XSF_YHZH><GMF_NSRSBH></GMF_NSRSBH><GMF_MC>个人</GMF_MC><GMF_DZDH></GMF_DZDH><GMF_YHZH>888888888888888</GMF_YHZH><GMF_YX>fapiaotest@baiwang.com</GMF_YX><GMF_SJH>15165253187</GMF_SJH><KPR>bw0701</KPR><SKR>bw0701</SKR><FHR>bw0701</FHR><YFP_DM></YFP_DM><YFP_HM></YFP_HM><JSHJ>58.50</JSHJ><HJJE>50.00</HJJE><HJSE>8.50</HJSE><BZ>备注</BZ><expand><name>名称</name><value>值</value></expand></COMMON_FPKJ_FPT><COMMON_FPKJ_XMXXS class=\"COMMON_FPKJ_XMXX\" size=\"2\"><COMMON_FPKJ_XMXX><FPHXZ>0</FPHXZ><XMMC>公牛（BULL）GN-H111</XMMC><GGXH></GGXH><DW></DW><XMSL>1</XMSL><XMDJ>10</XMDJ><XMJE>10.00</XMJE><SL>0.17</SL><SE>1.70</SE></COMMON_FPKJ_XMXX><COMMON_FPKJ_XMXX><FPHXZ>0</FPHXZ><XMMC>华潮 E386</XMMC><GGXH></GGXH><DW></DW><XMSL>2</XMSL><XMDJ>20</XMDJ><XMJE>40.00</XMJE><SL>0.17</SL><SE>6.80</SE></COMMON_FPKJ_XMXX></COMMON_FPKJ_XMXXS></REQUEST_COMMON_FPKJ></business>";
		/*String xml = readFile("E:\\kjfp\\kjfp.xml");*/
		Business business = unmarshallObject(xml.getBytes("gbk"));
		System.out.println(business.getREQUESTCOMMONFPKJ().getCOMMONFPKJFPT().getXSFMC());
	}
}
