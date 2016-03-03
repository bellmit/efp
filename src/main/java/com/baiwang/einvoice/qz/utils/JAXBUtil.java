package com.baiwang.einvoice.qz.utils;

import java.io.ByteArrayInputStream;
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
		/*String xml = readFile("E:\\kjfp\\kjfp.xml");*/
		Business business = unmarshallObject(xml.getBytes("utf-8"));
		
		System.out.println(XmlUtil.toSpecialInvoice(business.getREQUESTCOMMONFPKJ().getKpxx(), business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx()));
	}
}
