/**
 * 
 */
package com.baiwang.einvoice.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @project 百望股份电子发票前置平台
 * @package com.bwplat.eqz.qzadapter.invoice.impl
 * @file XmlToUbl.java 创建时间:2015年12月22日下午4:42:03
 * @title 开票接口
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2015 百望股份有限公司
 * @company 百望股份有限公司
 * @module 模块: 模块名称
 * @author   gkm
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
public class XmlToUbl {
	
	private static final Log logger = LogFactory.getLog(XmlToUbl.class);
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"gbk\"?><business id=\"FPKJ\" comment=\"发票开具\"><REQUEST_COMMON_FPKJ class=\"REQUEST_COMMON_FPKJ\">	<COMMON_FPKJ_FPT class=\"COMMON_FPKJ_FPT\"><FPQQLSH>499099992972</FPQQLSH><XTBS>005445545001</XTBS><JHKEY>0001111000111</JHKEY><KPLX>0</KPLX><XSF_NSRSBH>110101100015501</XSF_NSRSBH><XSF_MC>鐧炬椇鑲′唤鍖椾含浜垮煄娴嬭瘯2</XSF_MC><XSF_DZDH>11111</XSF_DZDH><XSF_YHZH></XSF_YHZH><GMF_NSRSBH>499099991232111</GMF_NSRSBH><GMF_MC>11111</GMF_MC><GMF_DZDH>11</GMF_DZDH><GMF_YHZH>11</GMF_YHZH><GMF_YX>aaa@baiwang.com</GMF_YX><GMF_SJH></GMF_SJH><KPR>bw0701</KPR><SKR>bw0701</SKR><FHR>bw0701</FHR><YFP_DM>1500000001</YFP_DM><YFP_HM>22213458</YFP_HM><JSHJ>1170.00</JSHJ><HJJE>1000.00</HJJE><HJSE>170.00</HJSE><BZ>澶囨敞</BZ><expand><name></name><value></value></expand></COMMON_FPKJ_FPT>	<COMMON_FPKJ_XMXXS class=\"COMMON_FPKJ_XMXX\" size=\"1\"><COMMON_FPKJ_XMXX><FPHXZ>0</FPHXZ><XMMC>yyy</XMMC><GGXH></GGXH><DW></DW><XMSL>1.000000</XMSL><XMDJ>1000.00</XMDJ><XMJE>1000.00</XMJE><SL>20</SL><SE>170.00</SE></COMMON_FPKJ_XMXX></COMMON_FPKJ_XMXXS></REQUEST_COMMON_FPKJ></business>";
		String ubl = xmlToUbl(xml );
		
		System.out.println(ubl);
	}
	
	public static String xmlToUbl(String xml){
		long st = System.currentTimeMillis();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); 
		sb.append("<Invoice ");
		sb.append(	"xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\" ");
		sb.append( 	"xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\" ");
		sb.append(  "xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\" "); 
		sb.append( 	"xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2\">");
		sb.append( 	 	"<ext:UBLExtensions>");
		sb.append( 	 		"<ext:UBLExtension>");
		sb.append( 	 			"<ext:ExtensionContent>");
		sb.append( 	 				"<bw:BaiwangExtensionContent xmlns:bw=\"http://www.baiwang.com.cn/baiwanginvoiceext\">");
		sb.append( 						"<bw:InvoiceIssueReqSeqNumber>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<FPQQLSH>", "</FPQQLSH>"));//1
		sb.append(						"</bw:InvoiceIssueReqSeqNumber>");
		sb.append(						"<bw:InvoiceIssueDeviceIdentifier>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<JQBH>", "</JQBH>")); //2
		sb.append(						"</bw:InvoiceIssueDeviceIdentifier>");
		sb.append(						"<bw:InvoiceCode>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<FP_DM>", "</FP_DM>"));//3
		sb.append(						"</bw:InvoiceCode>");
		sb.append(						"<bw:CryptoCode>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<FP_MW>", "</FP_MW>"));//4
		sb.append(						"</bw:CryptoCode>");
		sb.append(						"<bw:CheckCode>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<JYM>", "</JYM>"));//5
		sb.append(						"</bw:CheckCode>");
		sb.append(						"<bw:QRCode>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<EWM>", "</EWM>"));//6
		sb.append(						"</bw:QRCode>");
		sb.append(						"<bw:InvoiceIssuerPersonnelName>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<KPR>", "</KPR>"));//7
		sb.append(						"</bw:InvoiceIssuerPersonnelName>");
		sb.append(						"<bw:ReviewerName>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<FHR>", "</FHR>"));//8
		sb.append(						"</bw:ReviewerName>");
		sb.append(						"<bw:OriginalInvoiceCode>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<YFP_DM>", "</YFP_DM>"));//9
		sb.append(						"</bw:OriginalInvoiceCode>");
		sb.append(						"<bw:OriginalInvoiceNumber>");
		sb.append(							InvoiceUtil.getIntervalValue(xml, "<YFP_HM>", "</YFP_HM>"));//10
		sb.append(						"</bw:OriginalInvoiceNumber>");
		sb.append(						"<bw:KeyPairList>");//Expand/name/value
		
		int expand_index = xml.indexOf("<expand>");
		
		for(; expand_index >= 0; ){
			
			//System.out.println("第一个expand  name:"+ getIntervalValue(xml, "<name>", expand_index, "</name>"));
			
			sb.append(							"<bw:KeyPair>");
			sb.append(								"<bw:key>");
			sb.append(									InvoiceUtil.getIntervalValue(xml, "<name>", expand_index, "</name>"));
			sb.append(								"</bw:key>");
			sb.append(								"<bw:value>");
			sb.append(									InvoiceUtil.getIntervalValue(xml, "<value>", expand_index, "</value>"));
			sb.append(								"</bw:value>");
			sb.append(							"</bw:KeyPair>");
			
			int index_end = xml.indexOf("</expand>", expand_index);
			expand_index = xml.indexOf("<expand>", index_end);
		}
		if(expand_index == -1){
			sb.append(							"<bw:KeyPair>");
			sb.append(								"<bw:key>");
			sb.append(									"");
			sb.append(								"</bw:key>");
			sb.append(								"<bw:value>");
			sb.append(									"");
			sb.append(								"</bw:value>");
			sb.append(							"</bw:KeyPair>");
		}
		
		
		sb.append(						"</bw:KeyPairList>");
		sb.append(					"</bw:BaiwangExtensionContent>");
		sb.append(				"</ext:ExtensionContent>");
		sb.append(			"</ext:UBLExtension>");
 		sb.append(		"</ext:UBLExtensions>");
 		sb.append(		"<cbc:ID>");
 		sb.append(			InvoiceUtil.getIntervalValue(xml, "<FP_HM>", "</FP_HM>"));//11
 		sb.append(		"</cbc:ID>");
 		sb.append(		"<cbc:IssueDate>");
 		
 		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String issueDate = format.format(date);
		
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
		String issueTime = format2.format(date);
		
 		sb.append(			issueDate);//12
 		sb.append(		"</cbc:IssueDate>");
 		sb.append(		"<cbc:IssueTime>");
 		sb.append(			issueTime + ".0Z");//13
 		sb.append(		"</cbc:IssueTime>");
 		sb.append(		"<cbc:InvoiceTypeCode listAgencyID=\"6\" listID=\"UN/ECE 1001 Subset\" listVersionID=\"D08B\">");
 		if("0".equals(InvoiceUtil.getIntervalValue(xml, "<KPLX>", "</KPLX>"))){
 			sb.append(			"380");//14
 		}else{
 			sb.append(			"381");//14
 		}
 		sb.append(		"</cbc:InvoiceTypeCode>");
 		sb.append(		"<cbc:Note>");
 		sb.append(			InvoiceUtil.getIntervalValue(xml, "<BZ>", "</BZ>"));//15
 		sb.append(		"</cbc:Note>");
 		
 		sb.append(		"<cac:AccountingSupplierParty>");
 		sb.append(			"<cbc:CustomerAssignedAccountID>");
 		sb.append(				InvoiceUtil.getIntervalValue(xml, "<XSF_NSRSBH>", "</XSF_NSRSBH>") );//16
 		sb.append(			"</cbc:CustomerAssignedAccountID>");
 		sb.append(			"<cac:Party>");
 		sb.append(				"<cac:PartyIdentification>");
 		sb.append(					"<cbc:ID>");
 		sb.append(						InvoiceUtil.getIntervalValue(xml, "<XSF_YHZH>", "</XSF_YHZH>"));//17
 		sb.append(					"</cbc:ID>");
 		sb.append(				"</cac:PartyIdentification>");
 		sb.append(				"<cac:PartyName>");
 		sb.append(					"<cbc:Name>");
 		String xsfmc = "中国保险信息技术管理有限责任公司";/*InvoiceUtil.getIntervalValue(xml, "<XSF_MC>", "</XSF_MC>");
 		if (xsfmc!=null && xsfmc.indexOf("百旺")>=0)
 			xsfmc = xsfmc.replaceFirst("百旺", "百望");*/
 		sb.append(						xsfmc);//18
 		sb.append(					"</cbc:Name>");
 		sb.append(				"</cac:PartyName>");
 		sb.append(				"<cac:PostalAddress>");
 		sb.append(					"<cbc:StreetName>");
 		sb.append(						InvoiceUtil.getIntervalValue(xml, "<XSF_DZDH>", "</XSF_DZDH>"));//19
 		sb.append(					"</cbc:StreetName>");
 		sb.append(				"</cac:PostalAddress>");
 		sb.append(			"</cac:Party>");
 		sb.append(		"</cac:AccountingSupplierParty>");
 		
 		sb.append(		"<cac:AccountingCustomerParty>");
 		sb.append(			"<cbc:CustomerAssignedAccountID>");
 		sb.append(				InvoiceUtil.getIntervalValue(xml, "<GMF_NSRSBH>", "</GMF_NSRSBH>"));//20
 		sb.append(			"</cbc:CustomerAssignedAccountID>");
		sb.append(			"<cac:Party>");
		sb.append(				"<cac:PartyIdentification>");
		sb.append(					"<cbc:ID>");
		sb.append(						InvoiceUtil.getIntervalValue(xml, "<GMF_YHZH>", "</GMF_YHZH>"));//21
		sb.append(					"</cbc:ID>");
		sb.append(				"</cac:PartyIdentification>");
		sb.append(				"<cac:PartyName>");
		sb.append(					"<cbc:Name>");
		sb.append(						InvoiceUtil.getIntervalValue(xml, "<GMF_MC>", "</GMF_MC>"));//22
		sb.append(					"</cbc:Name>");
		sb.append(				"</cac:PartyName>");
		sb.append(				"<cac:PostalAddress>");
		sb.append(					"<cbc:StreetName>");
		sb.append(						InvoiceUtil.getIntervalValue(xml, "<GMF_DZDH>", "</GMF_DZDH>"));//23
		sb.append(					"</cbc:StreetName>");
		sb.append(				"</cac:PostalAddress>");
		sb.append(			"</cac:Party>");
		sb.append(			"<cac:BuyerContact>");
		sb.append(				"<cbc:Telephone>");
		sb.append(					InvoiceUtil.getIntervalValue(xml, "<GMF_SJH>", "</GMF_SJH>"));//24
		sb.append(				"</cbc:Telephone>");
		sb.append(				"<cbc:ElectronicMail>");
		sb.append(					InvoiceUtil.getIntervalValue(xml, "<GMF_YX>", "</GMF_YX>"));//25
		sb.append(				"</cbc:ElectronicMail>");
		sb.append(			"</cac:BuyerContact>");
		sb.append(		"</cac:AccountingCustomerParty>");
		sb.append(		"<cac:PayeeParty>");
		sb.append(			"<cac:PartyName>");
		sb.append(				"<cbc:Name>");
		sb.append(					InvoiceUtil.getIntervalValue(xml, "<SKR>", "</SKR>"));//26
		sb.append(				"</cbc:Name>");
		sb.append(			"</cac:PartyName>");
		sb.append(		"</cac:PayeeParty>");
		sb.append(		"<cac:TaxTotal>");
		sb.append(			"<cbc:TaxAmount currencyID=\"CNY\">");
		sb.append(				InvoiceUtil.getIntervalValue(xml, "<HJSE>", "</HJSE>"));//27
		sb.append(			"</cbc:TaxAmount>");
		sb.append(		"</cac:TaxTotal>");
		sb.append(		"<cac:LegalMonetaryTotal>");
		sb.append(			"<cbc:LineExtensionAmount currencyID=\"CNY\">");
		sb.append(				InvoiceUtil.getIntervalValue(xml, "<HJJE>", "</HJJE>"));//28
		sb.append(			"</cbc:LineExtensionAmount>");
		sb.append(			"<cbc:TaxExclusiveAmount currencyID=\"CNY\">");
		sb.append(				InvoiceUtil.getIntervalValue(xml, "<HJSE>", "</HJSE>"));//29
		sb.append(			"</cbc:TaxExclusiveAmount>");
		sb.append(			"<cbc:TaxInclusiveAmount currencyID=\"CNY\">");
		sb.append(				InvoiceUtil.getIntervalValue(xml, "<JSHJ>", "</JSHJ>"));//30
		sb.append(			"</cbc:TaxInclusiveAmount>");
		sb.append(			"<cbc:PayableAmount currencyID=\"CNY\">");
		sb.append(				InvoiceUtil.getIntervalValue(xml, "<JSHJ>", "</JSHJ>"));//31
		sb.append(			"</cbc:PayableAmount>");
		sb.append(		"</cac:LegalMonetaryTotal>");
		
		//项目明细，可多条(最大100条)
		int index_size = xml.indexOf("<COMMON_FPKJ_XMXXS class=\"COMMON_FPKJ_XMXX\" size=\"");
		int size = 1;
		try{
			size = Integer.parseInt(InvoiceUtil.getIntervalValue(xml, "size=\"", index_size, "\">"));
		}catch(Exception e){
			logger.info("xml 转换ubl 报文  size 异常");
			e.printStackTrace();
		}
		
		
		for(int i = 1 ; i <= size &&  index_size >= 0 ; i++ ){
			
			sb.append(		"<cac:InvoiceLine>");
			sb.append(			"<cbc:ID>");
			sb.append(				i);//position()   =====size
			sb.append(			"</cbc:ID>");   
			sb.append(			"<cbc:Note>");
			sb.append(				InvoiceUtil.getIntervalValue(xml, "<FPHXZ>", index_size, "</FPHXZ>"));//32
			sb.append(			"</cbc:Note>");
			sb.append(			"<cbc:InvoicedQuantity unitCode=\"");
			sb.append(			InvoiceUtil.getIntervalValue(xml, "<DW>", index_size, "</DW>"));//33 //dw
			sb.append(			"\">");
			sb.append(				InvoiceUtil.getIntervalValue(xml, "<XMSL>", index_size, "</XMSL>"));//34
			sb.append(			"</cbc:InvoicedQuantity>");
			sb.append(			"<cbc:LineExtensionAmount currencyID=\"CNY\">");
			sb.append(				InvoiceUtil.getIntervalValue(xml, "<XMJE>", index_size, "</XMJE>"));//35
			sb.append(			"</cbc:LineExtensionAmount>");
			sb.append(			"<cac:TaxTotal>");
			sb.append(				"<cbc:TaxAmount currencyID=\"CNY\">");
			sb.append(					InvoiceUtil.getIntervalValue(xml, "<SE>", index_size, "</SE>"));//36
			sb.append(				"</cbc:TaxAmount>");
			sb.append(				"<cac:TaxSubtotal>");
			sb.append(					"<cbc:TaxAmount currencyID=\"CNY\">");
			sb.append(						InvoiceUtil.getIntervalValue(xml, "<SE>", index_size, "</SE>"));//37
			sb.append(					"</cbc:TaxAmount>");
			sb.append(					"<cac:TaxCategory>");
			sb.append(						"<cbc:Percent>");
			sb.append(							InvoiceUtil.getIntervalValue(xml, "<SL>", index_size, "</SL>"));
			sb.append(						"</cbc:Percent>");//38  $$$$$$$$$$$$   substring-before()
			sb.append(						"<cac:TaxScheme/>");
			sb.append(					"</cac:TaxCategory>");
			sb.append(				"</cac:TaxSubtotal>");
			sb.append(			"</cac:TaxTotal>");
			sb.append(			"<cac:Item>");
			sb.append(				"<cbc:Name>");
			sb.append(					InvoiceUtil.getIntervalValue(xml, "<XMMC>", index_size, "</XMMC>"));//39
			sb.append(				"</cbc:Name>");
			sb.append(				"<cbc:ModelName>");
			sb.append(					InvoiceUtil.getIntervalValue(xml, "<GGXH>", index_size, "</GGXH>"));//40
			sb.append(				"</cbc:ModelName>");
			sb.append(			"</cac:Item>");
			sb.append(			"<cac:Price>");
			sb.append(				"<cbc:PriceAmount currencyID=\"CNY\">");
			sb.append(					InvoiceUtil.getIntervalValue(xml, "<XMDJ>", index_size, "</XMDJ>"));//41
			sb.append(				"</cbc:PriceAmount>");
			sb.append(			"</cac:Price>");
			sb.append(		"</cac:InvoiceLine>");
			
			int index_size_end = xml.indexOf("</COMMON_FPKJ_XMXX>", index_size);
			index_size = xml.indexOf("<COMMON_FPKJ_XMXX>", index_size_end);
			
		}
		
		
		
		sb.append("</Invoice>");
		
		
		
		
		
		long en = System.currentTimeMillis();
		long cha = st - en;
		logger.info("***************XmlToUbl===转换时间===>"+cha);
		
		return sb.toString();
	}
	
	
	private final static URL XSL_URL = Thread.currentThread()
			.getContextClassLoader().getResource("InvoiceToUbl-2.xsl");
//	private final static URL XSL_URL = Thread.currentThread().getContextClassLoader().getResource("InvoiceToUbl-0.1.xsl");
/*
 *  下面两2行会导致服务器上运行报错，注释掉 需要修改 TODO BW
    private final static Source xsltSource = new StreamSource(new File(XSL_URL.getFile()));

    private final static TransformerFactory transFact = TransformerFactory.newInstance();*/

    public XmlToUbl() {
    }
    
    /**
     * 
     *@name    xml转ubl
     *@description 相关说明
     *@time    创建时间:2015年12月22日下午4:50:16
     *@param invoice
     *@return
     *@throws TransformerException
     *@author   gkm
     *@history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static ByteArrayOutputStream convertToUbl(byte[] invoice) throws TransformerException {

    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Source xmlSource = new StreamSource(new ByteArrayInputStream(invoice));
        
        Transformer trans = null;
        Source xsltSource = new StreamSource(new File(XSL_URL.getFile()));
        TransformerFactory transFact = TransformerFactory.newInstance();
        try {
            trans = transFact.newTransformer(xsltSource);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        
        trans.transform(xmlSource, new StreamResult(baos));
        
        return baos;
    }
	
	

}
