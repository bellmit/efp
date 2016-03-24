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
				"<DDXXS LSH=\"66238462181003115098\">\r\n" + 
				"<DDXX>\r\n" + 
				"<ZDDH>100011</ZDDH> \r\n" + 
				"<HYID>会员id</HYID>\r\n" + 
				"<DDSJ>2016-03-22 17:23:57</DDSJ>\r\n" + 
				"<SQSJ>2016-03-22 11:42:00</SQSJ>\r\n" + 
				"<FKSJ>2016-03-22 17:23:57</FKSJ>\r\n" + 
				"<FPTTLX>00</FPTTLX>\r\n" + 
				"<FPTT>公司的专票</FPTT>\r\n" + 
				"<FPKHLX>00</FPKHLX>\r\n" + 
				"<SQRK>00</SQRK>\r\n" + 
				"<FPDQ>00</FPDQ>\r\n" + 
				"<SHR>收货人</SHR>\r\n" + 
				"<SHRDH>收货人电话</SHRDH>\r\n" + 
				"<JSDZ>寄送地址</JSDZ>\r\n" + 
				"<BZFP>备注发票信息</BZFP>\r\n" + 
				"<SJHM></SJHM>\r\n" + 
				"<YX></YX>\r\n" + 
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
				"<GMF_NSRSBH>11010800000000000007</GMF_NSRSBH> \r\n" + 
				"<GMF_MC>个人</GMF_MC> 	\r\n" + 
				"<GMF_DZ>北京海淀</GMF_DZ> 	\r\n" + 
				"<GMF_DH>12345678</GMF_DH> 			\r\n" + 
				"<GMF_YHZH>888888888888888</GMF_YHZH> 	\r\n" + 
				"<KPR>bw0701</KPR> 		\r\n" + 
				"<SKR>bw0701</SKR> 			\r\n" + 
				"<FHR>bw0701</FHR> 		\r\n" + 
				"<YFP_DM></YFP_DM> 		\r\n" + 
				"<YFP_HM></YFP_HM> 	\r\n" + 
				"<JSHJ>58.50</JSHJ> 		\r\n" + 
				"<HJJE></HJJE> 		\r\n" + 
				"<HJSE></HJSE> 		\r\n" + 
				"<BZ>备注</BZ> 	\r\n" + 
				"</COMMON_FPKJ_FPT>\r\n" + 
				"<COMMON_FPKJ_XMXXS> \r\n" + 
				"<COMMON_FPKJ_XMXX> 		\r\n" + 
				"<FPHXZ>0</FPHXZ> 				\r\n" + 
				"<XMMC>公牛（BULL）GN-H111</XMMC>\r\n" + 
				"<DW></DW> 		\r\n" + 
				"<GGXH></GGXH> 		\r\n" + 
				"<XMSL>1</XMSL> 		\r\n" + 
				"<XMDJ></XMDJ> 			\r\n" + 
				"<XMJE></XMJE> 			\r\n" + 
				"<SL>0.06</SL> 			\r\n" + 
				"<SE></SE> 			\r\n" + 
				"<HSBZ>0</HSBZ> 		\r\n" + 
				"</COMMON_FPKJ_XMXX> 	\r\n" + 
				"</COMMON_FPKJ_XMXXS> \r\n" + 
				"</REQUEST_COMMON_FPKJ>\r\n" + 
				"</business>";
			System.out.println(validateXml("wyyy.xsd", xml.getBytes("gbk")));
	}
}
