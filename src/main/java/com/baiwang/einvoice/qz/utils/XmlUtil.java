/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;

/**
  * @ClassName: XmlUtil
  * @Description: 封装纸质发票和电子发票报文
  * @author zhaowei
  * @date 2016年3月2日 下午2:58:29
  */
public class XmlUtil {

	public static boolean isEmpty(String str) {  
        if (null != str && str.trim().length() > 0)  
            return false;  
        return true;  
    }  
	
	public static String toSpecialInvoice(Kpxx kpxx, List<Fpmx> fpmxList) throws UnsupportedEncodingException{
		if (null == kpxx || null == fpmxList) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("\r\n");
		sb.append("<business id=\"10008\" comment=\"发票开具\">");
		sb.append("\r\n");
		sb.append("<body yylxdm=\"1\">");
		sb.append("\r\n");
		sb.append("<kpzdbs>"+""+"</kpzdbs>");//
		sb.append("\r\n");
		sb.append("<fplxdm>"+""+"</fplxdm>");//
		sb.append("\r\n");
		sb.append("<fpqqlsh>"+kpxx.getFpqqlsh()+"</fpqqlsh>");
		sb.append("\r\n");
		sb.append("<kplx>"+kpxx.getKPLX()+"</kplx>");
		sb.append("\r\n");
		sb.append("<tspz>00</tspz>");
		sb.append("\r\n");
		sb.append("<xhdwsbh>"+kpxx.getXSFNSRSBH()+"</xhdwsbh>");
		sb.append("\r\n");
		sb.append("<xhdwmc>"+kpxx.getXSFMC()+"</xhdwmc>");
		sb.append("\r\n");
		sb.append("<xhdwdzdh>"+kpxx.getXSFDZ() + kpxx.getXSFDH()+"</xhdwdzdh>");
		sb.append("\r\n");
		sb.append("<xhdwyhzh>"+kpxx.getGMFYHZH()+"</xhdwyhzh>");
		sb.append("\r\n");
		sb.append("<ghdwsbh>"+ kpxx.getGMFNSRSBH()+"</ghdwsbh>");
		sb.append("\r\n");
		sb.append("<ghdwmc>"+kpxx.getGMFMC()+"</ghdwmc>");
		sb.append("\r\n");
		sb.append("<ghdwdzdh>"+kpxx.getGMFDZ() + kpxx.getGMFDH()+"</ghdwdzdh>");
		sb.append("\r\n");
		sb.append("<ghdwyhzh>"+kpxx.getGMFYHZH()+"</ghdwyhzh>");
		
		int qdbz = 0;
		int fpmxListSize = fpmxList.size();
		if(fpmxListSize>0){
			qdbz = 1;
			sb.append("<qdbz>"+ qdbz+"</qdbz>");
			sb.append("\r\n");
			sb.append("<fyxm count=\""+fpmxListSize+"\">");
			sb.append("\r\n");
			for(int i=0;i<fpmxListSize;i++){
				Fpmx temp = fpmxList.get(i);
				sb.append("<group xh=\""+(i+1)+"\">");
				sb.append("\r\n");
				sb.append("<fphxz>"+temp.getFPHXZ()+"</fphxz>");
				sb.append("\r\n");
				sb.append("<spmc>"+temp.getXMMC()+"</spmc>");
				sb.append("\r\n");
				sb.append("<spsm></spsm>");
				sb.append("\r\n");
				sb.append("<ggxh>"+temp.getGGXH()+"</ggxh>");
				sb.append("\r\n");
				sb.append("<dw>"+temp.getDW()+"</dw>");
				sb.append("\r\n");
				sb.append("<spsl>"+temp.getXMSL()+"</spsl>");
				sb.append("\r\n");
				sb.append("<dj>"+ temp.getXMDJ()+"</dj>");
				sb.append("\r\n");
				sb.append("<je>"+ temp.getXMJE()+"</je>");
				sb.append("\r\n");
				sb.append("<sl>"+ temp.getSL()+"</sl>");
				sb.append("\r\n");
				sb.append("<se>"+ temp.getSE()+"</se>");
				sb.append("\r\n");
				sb.append("<hsbz>"+temp.getHSBZ()+"</hsbz>");
				sb.append("\r\n");
				sb.append("</group>");
				sb.append("\r\n");
			}
			sb.append("</fyxm>");
		}
		
		sb.append("\r\n");
		sb.append("<hjje>"+ kpxx.getHJJE() +"</hjje>");
		sb.append("\r\n");
		sb.append("<hjse>"+ kpxx.getHJSE()+"</hjse>");
		sb.append("\r\n");
		sb.append("<jshj>"+ kpxx.getJSHJ() +"</jshj>");
		sb.append("\r\n");
		sb.append("<bz>"+kpxx.getBZ()+"</bz>");
		sb.append("\r\n");
		sb.append("<skr>"+ kpxx.getSKR() +"</skr>");
		sb.append("\r\n");
		sb.append("<fhr>"+ kpxx.getFHR() +"</fhr>");
		sb.append("\r\n");
		sb.append("<kpr>"+ kpxx.getKPR() +"</kpr>");
		sb.append("\r\n");
		sb.append("<tzdbh></tzdbh>");
		sb.append("\r\n");
		sb.append("<yfpdm>"+ kpxx.getYFPDM() +"</yfpdm>");
		sb.append("\r\n");
		sb.append("<yfphm>"+ kpxx.getYFPHM() +"</yfphm>");
		sb.append("\r\n");
		sb.append("<qmcs></qmcs>");
		sb.append("\r\n");
		sb.append("</body>");
		sb.append("\r\n");
		sb.append("</business>");
		return sb.toString();
	}
	
	public static String toPlainInvoice(Kpxx kpxx, List<Fpmx> fpmxList) throws UnsupportedEncodingException{
		if (null == kpxx || null == fpmxList) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("\r\n");
		sb.append("<business id=\"10008\" comment=\"发票开具\">");
		sb.append("\r\n");
		sb.append("<body yylxdm=\"1\">");
		sb.append("\r\n");
		sb.append("<kpzdbs>"+""+"</kpzdbs>");//
		sb.append("\r\n");
		sb.append("<fplxdm>"+""+"</fplxdm>");//
		sb.append("\r\n");
		sb.append("<fpqqlsh>"+kpxx.getFpqqlsh()+"</fpqqlsh>");
		sb.append("\r\n");
		sb.append("<kplx>"+kpxx.getKPLX()+"</kplx>");
		sb.append("\r\n");
		sb.append("<tspz>00</tspz>");
		sb.append("\r\n");
		sb.append("<xhdwsbh>"+kpxx.getXSFNSRSBH()+"</xhdwsbh>");
		sb.append("\r\n");
		sb.append("<xhdwmc>"+kpxx.getXSFMC()+"</xhdwmc>");
		sb.append("\r\n");
		sb.append("<xhdwdzdh>"+kpxx.getXSFDZ() + kpxx.getXSFDH()+"</xhdwdzdh>");
		sb.append("\r\n");
		sb.append("<xhdwyhzh>"+kpxx.getGMFYHZH()+"</xhdwyhzh>");
		sb.append("\r\n");
		sb.append("<ghdwsbh>"+ kpxx.getGMFNSRSBH()+"</ghdwsbh>");
		sb.append("\r\n");
		sb.append("<ghdwmc>"+kpxx.getGMFMC()+"</ghdwmc>");
		sb.append("\r\n");
		sb.append("<ghdwdzdh>"+kpxx.getGMFDZ() + kpxx.getGMFDH()+"</ghdwdzdh>");
		sb.append("\r\n");
		sb.append("<ghdwyhzh>"+kpxx.getGMFYHZH()+"</ghdwyhzh>");
		
		int qdbz = 0;
		int fpmxListSize = fpmxList.size();
		if(fpmxListSize>0){
			qdbz = 1;
			sb.append("<qdbz>"+ qdbz+"</qdbz>");
			sb.append("\r\n");
			sb.append("<fyxm count=\""+fpmxListSize+"\">");
			sb.append("\r\n");
			for(int i=0;i<fpmxListSize;i++){
				Fpmx temp = fpmxList.get(i);
				sb.append("<group xh=\""+(i+1)+"\">");
				sb.append("\r\n");
				sb.append("<fphxz>"+temp.getFPHXZ()+"</fphxz>");
				sb.append("\r\n");
				sb.append("<spmc>"+temp.getXMMC()+"</spmc>");
				sb.append("\r\n");
				sb.append("<spsm></spsm>");
				sb.append("\r\n");
				sb.append("<ggxh>"+temp.getGGXH()+"</ggxh>");
				sb.append("\r\n");
				sb.append("<dw>"+temp.getDW()+"</dw>");
				sb.append("\r\n");
				sb.append("<spsl>"+temp.getXMSL()+"</spsl>");
				sb.append("\r\n");
				sb.append("<dj>"+ temp.getXMDJ()+"</dj>");
				sb.append("\r\n");
				sb.append("<je>"+ temp.getXMJE()+"</je>");
				sb.append("\r\n");
				sb.append("<sl>"+ temp.getSL()+"</sl>");
				sb.append("\r\n");
				sb.append("<se>"+ temp.getSE()+"</se>");
				sb.append("\r\n");
				sb.append("<hsbz>"+temp.getHSBZ()+"</hsbz>");
				sb.append("\r\n");
				sb.append("</group>");
				sb.append("\r\n");
			}
			sb.append("</fyxm>");
		}
		
		sb.append("\r\n");
		sb.append("<hjje>"+ kpxx.getHJJE() +"</hjje>");
		sb.append("\r\n");
		sb.append("<hjse>"+ kpxx.getHJSE()+"</hjse>");
		sb.append("\r\n");
		sb.append("<jshj>"+ kpxx.getJSHJ() +"</jshj>");
		sb.append("\r\n");
		sb.append("<bz>"+kpxx.getBZ()+"</bz>");
		sb.append("\r\n");
		sb.append("<skr>"+ kpxx.getSKR() +"</skr>");
		sb.append("\r\n");
		sb.append("<fhr>"+ kpxx.getFHR() +"</fhr>");
		sb.append("\r\n");
		sb.append("<kpr>"+ kpxx.getKPR() +"</kpr>");
		sb.append("\r\n");
		sb.append("<tzdbh></tzdbh>");
		sb.append("\r\n");
		sb.append("<yfpdm>"+ kpxx.getYFPDM() +"</yfpdm>");
		sb.append("\r\n");
		sb.append("<yfphm>"+ kpxx.getYFPHM() +"</yfphm>");
		sb.append("\r\n");
		sb.append("<qmcs></qmcs>");
		sb.append("\r\n");
		sb.append("</body>");
		sb.append("\r\n");
		sb.append("</business>");
		return sb.toString();
	}
	
	public static String toEInvoice(Kpxx kpxx, List<Fpmx> fpmxList) throws UnsupportedEncodingException{
		if (null == kpxx || null == fpmxList) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("\r\n");
		sb.append("<business id=\"FPKJ\" comment=\"发票开具\">");
		sb.append("\r\n");
		sb.append("<REQUEST_COMMON_FPKJ class=\"REQUEST_COMMON_FPKJ\">");
		sb.append("\r\n");
		sb.append("<COMMON_FPKJ_FPT class=\"COMMON_FPKJ_FPT\">");//
		sb.append("\r\n");
		sb.append("<FPQQLSH>"+kpxx.getFpqqlsh()+"</FPQQLSH>");//
		sb.append("\r\n");
		sb.append("<KPLX>"+kpxx.getKPLX()+"</KPLX>");
		sb.append("\r\n");
		sb.append("<XSF_NSRSBH>"+kpxx.getXSFNSRSBH()+"</XSF_NSRSBH>");
		sb.append("\r\n");
		sb.append("<XSF_MC>"+kpxx.getXSFMC()+"</XSF_MC>");
		sb.append("\r\n");
		sb.append("<XSF_DZDH>"+kpxx.getXSFDZ() + kpxx.getXSFDH()+"</XSF_DZDH>");
		sb.append("\r\n");
		sb.append("<XSF_YHZH>"+kpxx.getGMFYHZH()+"</XSF_YHZH>");
		sb.append("\r\n");
		sb.append("<GMF_NSRSBH>"+ kpxx.getGMFNSRSBH()+"</GMF_NSRSBH>");
		sb.append("\r\n");
		sb.append("<GMF_MC>"+kpxx.getGMFMC()+"</GMF_MC>");
		sb.append("\r\n");
		sb.append("<GMF_DZDH>"+kpxx.getGMFDZ() + kpxx.getGMFDH()+"</GMF_DZDH>");
		sb.append("\r\n");
		sb.append("<GMF_YHZH>"+kpxx.getGMFYHZH()+"</GMF_YHZH>");
		sb.append("\r\n");
		sb.append("<KPR>"+ kpxx.getKPR() +"</KPR>");
		sb.append("\r\n");
		sb.append("<SKR>"+ kpxx.getSKR() +"</SKR>");
		sb.append("\r\n");
		sb.append("<FHR>"+ kpxx.getFHR() +"</FHR>");
		sb.append("\r\n");
		sb.append("<YFP_DM>"+ kpxx.getYFPDM() +"</YFP_DM>");
		sb.append("\r\n");
		sb.append("<YFP_HM>"+ kpxx.getYFPHM() +"</YFP_HM>");
		sb.append("\r\n");
		sb.append("<HJJE>"+ kpxx.getHJJE() +"</HJJE>");
		sb.append("\r\n");
		sb.append("<HJSE>"+ kpxx.getHJSE()+"</HJSE>");
		sb.append("\r\n");
		sb.append("<JSHJ>"+ kpxx.getJSHJ() +"</JSHJ>");
		sb.append("\r\n");
		sb.append("<BZ>"+kpxx.getBZ()+"</BZ>");
		sb.append("\r\n");
		sb.append("</COMMON_FPKJ_FPT>");
		sb.append("\r\n");
		int fpmxListSize = fpmxList.size();
		if(fpmxListSize>0){
			sb.append("<COMMON_FPKJ_XMXXS class=\"COMMON_FPKJ_XMXX\" size=\""+fpmxListSize+"\">");
			sb.append("\r\n");
			for(int i=0;i<fpmxListSize;i++){
				Fpmx temp = fpmxList.get(i);
				sb.append("<COMMON_FPKJ_XMXX>");
				sb.append("\r\n");
				sb.append("<FPHXZ>"+temp.getFPHXZ()+"</FPHXZ>");
				sb.append("\r\n");
				sb.append("<XMMC>"+temp.getXMMC()+"</XMMC>");
				sb.append("\r\n");
				sb.append("<GGXH>"+temp.getGGXH()+"</GGXH>");
				sb.append("\r\n");
				sb.append("<DW>"+temp.getDW()+"</DW>");
				sb.append("\r\n");
				sb.append("<XMSL>"+temp.getXMSL()+"</XMSL>");
				sb.append("\r\n");
				sb.append("<XMDJ>"+ temp.getXMDJ()+"</XMDJ>");
				sb.append("\r\n");
				sb.append("<XMJE>"+ temp.getXMJE()+"</XMJE>");
				sb.append("\r\n");
				sb.append("<SL>"+ temp.getSL()+"</SL>");
				sb.append("\r\n");
				sb.append("<SE>"+ temp.getSE()+"</SE>");
				sb.append("\r\n");
				sb.append("</COMMON_FPKJ_XMXX>");
				sb.append("\r\n");
			}
			sb.append("</COMMON_FPKJ_XMXXS>");
		}
		
		sb.append("\r\n");
		sb.append("</REQUEST_COMMON_FPKJ>");
		sb.append("\r\n");
		sb.append("</business>");
		return sb.toString();
	}
	
	public static String random() {
		String str = "";
		for (int i = 0; i < 20; i++) {
			str += String.valueOf((ThreadLocalRandom.current().nextInt(0, 9)));
		}
		return str;
	}
	public static void main(String[] args) {
			  String str="";
			  for(int i=0;i<20;i++){
			   str+=String.valueOf((ThreadLocalRandom.current().nextInt(0, 9)));
			  }
			  System.out.println(str);
	}
}
