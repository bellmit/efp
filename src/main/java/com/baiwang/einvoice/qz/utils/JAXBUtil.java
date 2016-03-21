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
		
		String xml = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n" + 
				"<business id=\"FPKJ\">\n" + 
				"	<CUSTOMDATA>\n" + 
				"		<ZDDH>40006</ZDDH>\n" + 
				"		<FDDH>001</FDDH>\n" + 
				"		<SQR>张三4</SQR>\n" + 
				"		<HYM>偶尔上会网</HYM>\n" + 
				"		<HYID>861124106</HYID>\n" + 
				"		<DDSJ>2016年3月5日</DDSJ>\n" + 
				"		<SQSJ>2016年3月5日</SQSJ>\n" + 
				"		<SPZL>服务费</SPZL>\n" + 
				"		<SQRK>ERP</SQRK>\n" + 
				"		<SHR>李四</SHR>\n" + 
				"		<SHRDH>13718099648</SHRDH>\n" + 
				"		<JSDZ>北京市海淀区亿城国际7层711</JSDZ>\n" + 
				"		<YJSJ>2016年3月5日</YJSJ>\n" + 
				"		<FHR>复核人XXX</FHR>\n" + 
				"		<WLGS>申通</WLGS>\n" + 
				"		<WLDH>1234567890</WLDH>\n" + 
				"		<TKZT>已退款</TKZT>\n" + 
				"	</CUSTOMDATA>\n" + 
				"	<REQUEST_COMMON_FPKJ>\n" + 
				"		<COMMON_FPKJ_FPT>\n" + 
				"			<FPLX>026</FPLX>\n" + 
				"			<KPLX>0</KPLX>\n" + 
				"			<XSF_NSRSBH>11010800000000000006</XSF_NSRSBH>\n" + 
				"			<XSF_MC>百旺股份6</XSF_MC>\n" + 
				"			<XSF_DZ>销售方地址</XSF_DZ>\n" + 
				"			<XSF_DH>11012345678</XSF_DH>\n" + 
				"			<XSF_YHZH>622848265825566332</XSF_YHZH>\n" + 
				"			<GMF_NSRSBH></GMF_NSRSBH>\n" + 
				"			<GMF_MC>我是谁</GMF_MC>\n" + 
				"			<GMF_DZ></GMF_DZ>\n" + 
				"			<GMF_DH></GMF_DH>\n" + 
				"			<GMF_YHZH>888888888888888</GMF_YHZH>\n" + 
				"			<KPR>bw0701</KPR>\n" + 
				"			<SKR>bw0701</SKR>\n" + 
				"			<FHR>bw0701</FHR>\n" + 
				"			<YFP_DM></YFP_DM>\n" + 
				"			<YFP_HM></YFP_HM>\n" + 
				"			<JSHJ>58.50</JSHJ>\n" + 
				"			<HJJE>50.00</HJJE>\n" + 
				"			<HJSE>8.50</HJSE>\n" + 
				"			<BZ>备注</BZ>\n" + 
				"		</COMMON_FPKJ_FPT>\n" + 
				"		<COMMON_FPKJ_XMXXS>\n" + 
				"			<COMMON_FPKJ_XMXX>\n" + 
				"				<FPHXZ>0</FPHXZ>\n" + 
				"				<XMMC>公牛（BULL）GN-H111</XMMC>\n" + 
				"				<GGXH></GGXH>\n" + 
				"				<DW></DW>\n" + 
				"				<XMSL>1</XMSL>\n" + 
				"				<XMDJ>10.00</XMDJ>\n" + 
				"				<XMJE>10.00</XMJE>\n" + 
				"				<SL>0.17</SL>\n" + 
				"				<SE>1.7</SE>\n" + 
				"				<HSBZ>0</HSBZ>\n" + 
				"			</COMMON_FPKJ_XMXX>\n" + 
				"			<COMMON_FPKJ_XMXX>\n" + 
				"				<FPHXZ>0</FPHXZ>\n" + 
				"				<XMMC>华潮 E386</XMMC>\n" + 
				"				<GGXH></GGXH>\n" + 
				"				<DW></DW>\n" + 
				"				<XMSL>2</XMSL>\n" + 
				"				<XMDJ>20.00</XMDJ>\n" + 
				"				<XMJE>40.00</XMJE>\n" + 
				"				<SL>0.17</SL>\n" + 
				"				<SE>6.80</SE>\n" + 
				"				<HSBZ>0</HSBZ>\n" + 
				"			</COMMON_FPKJ_XMXX>\n" + 
				"		</COMMON_FPKJ_XMXXS>\n" + 
				"	</REQUEST_COMMON_FPKJ>\n" + 
				"</business>";
		/*String xml = readFile("E:\\kjfp\\kjfp.xml");*/
		Business business = unmarshallObject(xml.getBytes("gbk"));
		//System.out.println(XmlUtil.toPlainInvoice(business.getREQUESTCOMMONFPKJ().getKpxx(), business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx()));
	}
}
