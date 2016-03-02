/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.utils;

import java.util.regex.Pattern;

import com.baiwang.einvoice.util.InvoiceUtil;

/**
  * @ClassName: XmlCheck
  * @Description: xml校验
  * @author zhaowei
  * @date 2016年2月29日 下午4:21:05
  */
public class XmlCheck {

	public static String checkXml(String xml){
		
		String fpqqlsh = InvoiceUtil.getIntervalValue(xml,"<FPQQLSH>","</FPQQLSH>");
		if( "".equals(fpqqlsh) ){
			return InvoiceUtil.backMsg("4100", "4100:发票请求流水号未填写", xml);
		}
		if( fpqqlsh.length() > 20 ){
			return InvoiceUtil.backMsg("4100", "4100:发票请求流水号最大长度为20位", xml);
		}
		
		String kplx = InvoiceUtil.getIntervalValue(xml,"<KPLX>","</KPLX>");
		if(!("0".equals(kplx) || "1".equals(kplx))){
			return InvoiceUtil.backMsg("4100", "4100:开票类型格式不正确（0-蓝字发票；1-红字发票）", xml);
		}
		
		String xsfnsrsbh = InvoiceUtil.getIntervalValue(xml,"<XSF_NSRSBH>","</XSF_NSRSBH>");
		if("".equals(xsfnsrsbh)){
			return InvoiceUtil.backMsg("4100", "4100:销售方纳税人识别号未填写", xml);
		}
		if( xsfnsrsbh.length() > 20 ){
			return InvoiceUtil.backMsg("4100", "4100:销售方纳税人识别号最大长度为20位", xml);
		}
		
		String xsfmc = InvoiceUtil.getIntervalValue(xml,"<XSF_MC>","</XSF_MC>");
		if("".equals(xsfmc)){
			return InvoiceUtil.backMsg("4100", "4100:销售方名称未填写", xml);
		}
		if( xsfmc.length() > 100 ){
			return InvoiceUtil.backMsg("4100", "4100:销售方名称最大长度为100位", xml);
		}
		
		String xsfdzdh = InvoiceUtil.getIntervalValue(xml,"<XSF_DZDH>","</XSF_DZDH>");
		if("".equals(xsfdzdh)){
			return InvoiceUtil.backMsg("4100", "4100:销售方地址电话未填写", xml);
		}
		if( xsfdzdh.length() > 100 ){
			return InvoiceUtil.backMsg("4100", "4100:销售方地址电话最大长度为100位", xml);
		}
		
		String xsfyhzh = InvoiceUtil.getIntervalValue(xml,"<XSF_YHZH>","</XSF_YHZH>");
		if( !"".equals(xsfyhzh) && xsfdzdh.length() > 100 ){
			return InvoiceUtil.backMsg("4100", "4100:销售方银行账户最大长度为100位", xml);
		}

		String gmfnsrsbh = InvoiceUtil.getIntervalValue(xml,"<GMF_NSRSBH>","</GMF_NSRSBH>");
		if( !"".equals(gmfnsrsbh) && gmfnsrsbh.length() > 20 ){
			return InvoiceUtil.backMsg("4100", "4100:购买方纳税人识别号最大长度为20位", xml);
		}

		String gmfmc = InvoiceUtil.getIntervalValue(xml,"<GMF_MC>","</GMF_MC>");
		if("".equals(gmfmc)){
			return InvoiceUtil.backMsg("4100", "4100:购买方名称未填写", xml);
		}
		if( gmfmc.length() > 100 ){
			return InvoiceUtil.backMsg("4100", "4100:销售方名称最大长度为100位", xml);
		}
		
		String gmfdzdh = InvoiceUtil.getIntervalValue(xml,"<GMF_DZDH>","</GMF_DZDH>");
		if( !"".equals(gmfdzdh) && gmfdzdh.length() > 100 ){
			return InvoiceUtil.backMsg("4100", "4100:购买方地址电话最大长度为100位", xml);
		}
		
		String yx = InvoiceUtil.getIntervalValue(xml,"<GMF_YX>","</GMF_YX>");
		String dh = InvoiceUtil.getIntervalValue(xml,"<GMF_SJH>","</GMF_SJH>");
//		if( "".equals(yx) && "".equals(dh) ){
//			return InvoiceUtil.backMsg("4100", "4100:购买方手机号、邮箱必填一个。", xml);
//		}
		if(!(null == yx || "".equals(yx))){
			String check = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	        if(!Pattern.compile(check).matcher(yx).matches()){
	        	return InvoiceUtil.backMsg("4100", "4100:购买方邮箱格式不正确", xml);
	        }
		}
		if(!(null == dh || "".equals(dh))){
            if(!Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))[0-9]{8}$").matcher(dh).matches()){
            	return InvoiceUtil.backMsg("4100", "4100:购买方手机号格式不正确", xml);
	        }
		}
		
		String gmfyhzh = InvoiceUtil.getIntervalValue(xml,"<GMF_YHZH>","</GMF_YHZH>");
		if( !"".equals(gmfyhzh) && gmfyhzh.length() > 100 ){
			return InvoiceUtil.backMsg("4100", "4100:购买方银行账号最大长度为100位", xml);
		}
		
		String kpr = InvoiceUtil.getIntervalValue(xml,"<KPR>","</KPR>");
		if("".equals(kpr)){
			return InvoiceUtil.backMsg("4100", "4100:开票人未填写", xml);
		}
		if( kpr.length() > 8 ){
			return InvoiceUtil.backMsg("4100", "4100:开票人最大长度为8位", xml);
		}
		
		String skr = InvoiceUtil.getIntervalValue(xml,"<SKR>","</SKR>");
		if( !"".equals(skr) && skr.length() > 8 ){
			return InvoiceUtil.backMsg("4100", "4100:收款人最大长度为8位", xml);
		}
		
		String fhr = InvoiceUtil.getIntervalValue(xml,"<FHR>","</FHR>");
		if( !"".equals(fhr) && fhr.length() > 8 ){
			return InvoiceUtil.backMsg("4100", "4100:复核人最大长度为8位", xml);
		}
		
		String yfpdm = InvoiceUtil.getIntervalValue(xml,"<YFP_DM>","</YFP_DM>");
		if( "1".equals(kplx) && "".equals(yfpdm)){
			return InvoiceUtil.backMsg("4100", "4100:红字发票原发票代码未填写", xml);
		}
		if( "1".equals(kplx) && yfpdm.length() > 12){
			return InvoiceUtil.backMsg("4100", "红字发票原发票代码最大长度为12位", xml);
		}
		
		String yfphm = InvoiceUtil.getIntervalValue(xml,"<YFP_HM>","</YFP_HM>");
		if( "1".equals(kplx) && "".equals(yfphm)){
			return InvoiceUtil.backMsg("4100", "4100:红字发票原发票号码未填写", xml);
		}
		if( "1".equals(kplx) && yfphm.length() > 8){
			return InvoiceUtil.backMsg("4100", "4100:红字发票原发票号码最大长度为8位", xml);
		}
		
		String jshj = InvoiceUtil.getIntervalValue(xml,"<JSHJ>","</JSHJ>");
		if("".equals(jshj) || !Pattern.compile("^[-]?[0-9]+(.[0-9]{1,2})?$").matcher(jshj).matches()){
			return InvoiceUtil.backMsg("4100", "4100:价税合计未填写或为保留两位小数", xml);
		}
		
		String hjje = InvoiceUtil.getIntervalValue(xml,"<HJJE>","</HJJE>");
		if("".equals(hjje) || !Pattern.compile("^[-]?[0-9]+(.[0-9]{1,2})?$").matcher(hjje).matches()){
			return InvoiceUtil.backMsg("4100", "4100:合计金额未填写或为保留两位小数", xml);
		}
		
		String hjse = InvoiceUtil.getIntervalValue(xml,"<HJSE>","</HJSE>");
		if("".equals(hjse) || !Pattern.compile("^[-]?[0-9]+(.[0-9]{1,2})?$").matcher(hjse).matches()){
			return InvoiceUtil.backMsg("4100", "4100:合计税额未填写或为保留两位小数", xml);
		}
		
		String bz = InvoiceUtil.getIntervalValue(xml,"<BZ>","</BZ>");
		if( !"".equals(bz) && bz.length() > 200 ){
			return InvoiceUtil.backMsg("4100", "4100:备注最大长度为200位", xml);
		}
		
		int expand = xml.indexOf("<expand>");
		if ( expand > 0 ) {
			for(int i = 0; expand > 0 ; i++){
				String name = InvoiceUtil.getIntervalValue(xml, "<name>", expand, "</name>");
				if(!"".equals(name) && name.length() > 50){
					return InvoiceUtil.backMsg("4100", "4100:扩展"+i+"name最大长度为50位", xml);
				}
				
				String value = InvoiceUtil.getIntervalValue(xml, "<value>", expand, "</value>");
				if(!"".equals(value) && value.length() > 200){
					return InvoiceUtil.backMsg("4100", "4100:扩展"+i+"value最大长度为200位", xml);
				}
				
				int expandEnd = xml.indexOf("</expand>", expand);
				expand = xml.indexOf("<expand>", expandEnd);
			}
		}
		
		int xmxx = xml.indexOf("<COMMON_FPKJ_XMXX>");
		if ( xmxx > 0 ) {
			for(int i = 1; xmxx > 0 ; i++){
				
				String fphxz = InvoiceUtil.getIntervalValue(xml, "<FPHXZ>", xmxx, "</FPHXZ>");
				if(!("0".equals(fphxz) || "1".equals(fphxz) || "2".equals(fphxz))){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"发票行性质格式不正确（0正常行、1折扣行、2被折扣行）", xml);
				}
				
				String xmmc = InvoiceUtil.getIntervalValue(xml, "<XMMC>", xmxx, "</XMMC>");
				if("".equals(xmmc)){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"项目名称未填写", xml);
				}
				if( xmmc.length() > 90 ){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"项目名称最大长度为90位", xml);
				}
				
				String dw = InvoiceUtil.getIntervalValue(xml, "<DW>", xmxx, "</DW>");
				if( !"".equals(dw) && dw.length() > 20 ){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"单位最大长度为20位", xml);
				}
				
				String ggxh = InvoiceUtil.getIntervalValue(xml, "<GGXH>", xmxx, "</GGXH>");
				if( !"".equals(ggxh) && ggxh.length() > 40 ){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"规格型号最大长度为20位", xml);
				}
				
				String xmsl = InvoiceUtil.getIntervalValue(xml, "<XMSL>", xmxx, "</XMSL>");
				if(!"".equals(xmsl) && !Pattern.compile("^[-]?[0-9]+(.[0-9]{1,6})?$").matcher(xmsl).matches()){
//				if(!"".equals(xmsl) && !Pattern.compile("^(([1-9][0-9]{0,5})|0)(.[0-9]{1,6})?$").matcher(xmsl).matches()){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"项目数量最多六位小数", xml);
				}
				
				String xmdj = InvoiceUtil.getIntervalValue(xml, "<XMDJ>", xmxx, "</XMDJ>");
				if(!"".equals(xmdj) && !Pattern.compile("^[0-9]+(.[0-9]{1,6})?$").matcher(xmdj).matches()){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"项目单价最多六位小数", xml);
				}
				
				String xmje = InvoiceUtil.getIntervalValue(xml, "<XMJE>", xmxx, "</XMJE>");
				if("".equals(xmje) || !Pattern.compile("^[-]?[0-9]+(.[0-9]{1,2})?$").matcher(xmje).matches()){
//				if("".equals(xmje) || !Pattern.compile("^(([1-9][0-9]{0,9})|0).[0-9]{2}$").matcher(xmje).matches()){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"项目金额未填写或未保留两位小数", xml);
				}
				
				String sl = InvoiceUtil.getIntervalValue(xml, "<SL>", xmxx, "</SL>");
				if("".equals(sl)){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"税率未填写。", xml);
				}
				if("".equals(sl) || !Pattern.compile("^(([1-9][0-9]{0,5})|0)(.[0-9]{1,6})?$").matcher(sl).matches()){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"税率最多六位小数", xml);
				}
				
				String se = InvoiceUtil.getIntervalValue(xml, "<SE>", xmxx, "</SE>");
				if("".equals(se) || !Pattern.compile("^[-]?[0-9]+(.[0-9]{1,2})?$").matcher(se).matches()){
					return InvoiceUtil.backMsg("4100", "4100:项目明细"+i+"税额未填写或未保留两位小数", xml);
				}
				
				
				int xmxxEnd = xml.indexOf("</COMMON_FPKJ_XMXX>", xmxx);
				xmxx = xml.indexOf("<COMMON_FPKJ_XMXX>", xmxxEnd);
			}
		}else{
			return InvoiceUtil.backMsg("4100", "4100:项目明细未填写", xml);
		}
		
		
		
		return "0000";
	}
}
