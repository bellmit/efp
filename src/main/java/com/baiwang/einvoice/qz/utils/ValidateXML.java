/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;


/**
 * @ClassName: ValidateXML
 * @Description: 通过xsd验证xml是符合规则
 * @author wsdoing
 * @date 2016年3月2日 下午5:23:47
 */
public class ValidateXML {

	public static boolean validateXml(String xsdName, byte[] xml){
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		InputStream is = ValidateXML.class.getResourceAsStream(xsdName);
		Source xsltSource = new StreamSource(is);
		try {
			Schema schema = schemaFactory.newSchema(xsltSource);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(new ByteArrayInputStream(xml));
			validator.validate(source);
			return true;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String xml = "<?xml version=\"1.0\" encoding=\"gbk\"?> <business id=\"FPKJ\"> 	\r\n" + 
				"<CUSTOMDATA>\r\n" + 
				"<DDXXS LSH=\"11112222333344445555\">\r\n" + 
				"<DDXX>\r\n" + 
				"<ZDDH>100008</ZDDH> \r\n" + 
				"<SQR>张三</SQR>\r\n" + 
				"<HYM>会员名</HYM>\r\n" + 
				"<HYID>会员id</HYID>\r\n" + 
				"<DDSJ>2016-3-21 17:26:31</DDSJ>\r\n" + 
				"<SQSJ>2016-3-21 17:26:53</SQSJ>\r\n" + 
				"<FPTT>个人</FPTT>\r\n" + 
				"<FPKH>B2B/B2C</FPKH>\r\n" + 
				"<SPZL>服务费</SPZL>\r\n" + 
				"<SQRK>erp</SQRK>\r\n" + 
				"<FPDQ>发票地区</FPDQ>\r\n" + 
				"<SHR>收货人</SHR>\r\n" + 
				"<SHRDH>收货人电话</SHRDH>\r\n" + 
				"<JSDZ>寄送地址</JSDZ>\r\n" + 
				"<TKZT>退款状态</TKZT>\r\n" + 
				"<FPCLZT>发票处理状态</FPCLZT>\r\n" + 
				"<BZFP>备注发票信息</BZFP>\r\n" + 
				"</DDXX>\r\n" + 
				"</DDXXS>\r\n" + 
				"</CUSTOMDATA>\r\n" + 
				"<REQUEST_COMMON_FPKJ>\r\n" + 
				"<COMMON_FPKJ_FPT>\r\n" + 
				"<FPLX>026</FPLX>\r\n" + 
				"<KPLX>0</KPLX>\r\n" + 
				"<XSF_NSRSBH>11010800000000000006</XSF_NSRSBH>\r\n" + 
				"<XSF_MC>百旺股份6</XSF_MC>\r\n" + 
				"<XSF_DZ>销售方地址</XSF_DZ> \r\n" + 
				"<XSF_DH>11012345678</XSF_DH>\r\n" + 
				"<XSF_YHZH>622848265825566332</XSF_YHZH>\r\n" + 
				"<GMF_NSRSBH></GMF_NSRSBH> \r\n" + 
				"<GMF_MC>个人</GMF_MC> 	\r\n" + 
				"<GMF_DZ></GMF_DZ> 	\r\n" + 
				"<GMF_DH></GMF_DH> 			\r\n" + 
				"<GMF_YHZH>888888888888888</GMF_YHZH> 	\r\n" + 
				"<KPR>bw0701</KPR> 		\r\n" + 
				"<SKR>bw0701</SKR> 			\r\n" + 
				"<FHR>bw0701</FHR> 		\r\n" + 
				"<YFP_DM></YFP_DM> 		\r\n" + 
				"<YFP_HM></YFP_HM> 	\r\n" + 
				"<JSHJ>58.50</JSHJ> 		\r\n" + 
				"<HJJE>50.00</HJJE> 		\r\n" + 
				"<HJSE>8.50</HJSE> 		\r\n" + 
				"<BZ>备注</BZ> 	\r\n" + 
				"</COMMON_FPKJ_FPT>\r\n" + 
				"<COMMON_FPKJ_XMXXS> \r\n" + 
				"<COMMON_FPKJ_XMXX> 		\r\n" + 
				"<FPHXZ>0</FPHXZ> 				\r\n" + 
				"<XMMC>公牛（BULL）GN-H111</XMMC>\r\n" + 
				"<DW></DW> 		\r\n" + 
				"<GGXH></GGXH> 		\r\n" + 
				"<XMSL>1</XMSL> 		\r\n" + 
				"<XMDJ>10.00</XMDJ> 			\r\n" + 
				"<XMJE>10.00</XMJE> 			\r\n" + 
				"<SL>0.17</SL> 			\r\n" + 
				"<SE>1.7</SE> 			\r\n" + 
				"<HSBZ>0</HSBZ> 		\r\n" + 
				"</COMMON_FPKJ_XMXX> 	\r\n" + 
				"<COMMON_FPKJ_XMXX> 		\r\n" + 
				"<FPHXZ>0</FPHXZ> 		\r\n" + 
				"<XMMC>华潮 E386</XMMC> 	\r\n" + 
				"<DW></DW> 			\r\n" + 
				"<GGXH></GGXH> 			\r\n" + 
				"<XMSL>2</XMSL> 		\r\n" + 
				"<XMDJ>20.00</XMDJ> 			\r\n" + 
				"<XMJE>40.00</XMJE> 			\r\n" + 
				"<SL>0.17</SL> 			\r\n" + 
				"<SE>6.80</SE> 			\r\n" + 
				"<HSBZ>0</HSBZ> 		\r\n" + 
				"</COMMON_FPKJ_XMXX> \r\n" + 
				"</COMMON_FPKJ_XMXXS> \r\n" + 
				"</REQUEST_COMMON_FPKJ>\r\n" + 
				"</business>";
			System.out.println(validateXml("wyyy.xsd", xml.getBytes("gbk")));
	}
}
