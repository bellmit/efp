/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;


/**
 * @ClassName: ValidateXML
 * @Description: TODO
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
		System.out.println(1231);
		return false;
	}
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<business id=\"FPKJ\">\n" + 
				"	<CUSTOMDATA>\n" + 
				"		<DD_HM>订单号码</DD_HM>\n" + 
				"		<DD_SS_DQ>订单所属地区</DD_SS_DQ>\n" + 
				"		<DD_YW_LX>订单业务类型，B2B,B2C</DD_YW_LX>\n" + 
				"		<DD_SJ>订单生成时间</DD_SJ>\n" + 
				"	</CUSTOMDATA>\n" + 
				"	<REQUEST_COMMON_FPKJ>\n" + 
				"		<COMMON_FPKJ_FPT>\n" + 
				"			<FPLX>发票类型</FPLX>\n" + 
				"			<KPLX>开票类型</KPLX>\n" + 
				"			<XSF_NSRSBH>销售方纳税人识别号</XSF_NSRSBH>\n" + 
				"			<XSF_MC>销售方名称</XSF_MC>\n" + 
				"			<XSF_DZ>销售方地址</XSF_DZ>\n" + 
				"			<XSF_DH>销售方电话</XSF_DH>\n" + 
				"			<XSF_YHZH>销售方银行账号</XSF_YHZH>\n" + 
				"			<GMF_NSRSBH>购买方纳税人识别号</GMF_NSRSBH>\n" + 
				"			<GMF_MC>购买方名称</GMF_MC>\n" + 
				"			<GMF_DZ>购买方地址</GMF_DZ>\n" + 
				"			<GMF_DH>购买方电话</GMF_DH>\n" + 
				"			<GMF_YHZH>购买方银行账号</GMF_YHZH>\n" + 
				"			<KPR>开票人</KPR>\n" + 
				"			<SKR>收款人</SKR>\n" + 
				"			<FHR>复核人</FHR>\n" + 
				"			<YFP_DM>原发票代码</YFP_DM>\n" + 
				"			<YFP_HM>原发票号码</YFP_HM>\n" + 
				"			<JSHJ>价税合计</JSHJ>\n" + 
				"			<HJJE>合计金额</HJJE>\n" + 
				"			<HJSE>合计税额</HJSE>\n" + 
				"			<BZ>备注</BZ>\n" + 
				"		</COMMON_FPKJ_FPT>\n" + 
				"		<COMMON_FPKJ_XMXXS>\n" + 
				"			<COMMON_FPKJ_XMXX>\n" + 
				"				<FPHXZ>发票行性质</FPHXZ>\n" + 
				"				<XMMC>项目名称</XMMC>\n" + 
				"				<GGXH>规格型号</GGXH>\n" + 
				"				<DW>单位</DW>\n" + 
				"				<XMSL>项目数量</XMSL>\n" + 
				"				<XMDJ>项目单价</XMDJ>\n" + 
				"				<XMJE>项目金额</XMJE>\n" + 
				"				<SL>税率</SL>\n" + 
				"				<SE>税额</SE>\n" + 
				"				<HSBZ>含税标志</HSBZ>\n" + 
				"			</COMMON_FPKJ_XMXX>\n" + 
				"			<COMMON_FPKJ_XMXX>\n" + 
				"				<FPHXZ>发票行性质</FPHXZ>\n" + 
				"				<XMMC>项目名称</XMMC>\n" + 
				"				<GGXH>规格型号</GGXH>\n" + 
				"				<DW>单位</DW>\n" + 
				"				<XMSL>项目数量</XMSL>\n" + 
				"				<XMDJ>项目单价</XMDJ>\n" + 
				"				<XMJE>项目金额</XMJE>\n" + 
				"				<SL>税率</SL>\n" + 
				"				<SE>税额</SE>\n" + 
				"				<HSBZ>含税标志</HSBZ>\n" + 
				"			</COMMON_FPKJ_XMXX>\n" + 
				"		</COMMON_FPKJ_XMXXS>\n" + 
				"	</REQUEST_COMMON_FPKJ>\n" + 
				"</business>";
			System.out.println(validateXml("wyyy.xsd", xml.getBytes()));
	}
}
