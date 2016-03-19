/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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
	
	public static String toSpecialInvoice(Kpxx kpxx, List<Fpmx> fpmxList,String kpzdbs) throws UnsupportedEncodingException{
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
		sb.append("<kpzdbs>"+kpzdbs+"</kpzdbs>");//
		sb.append("\r\n");
		sb.append("<fplxdm>"+kpxx.getFplx()+"</fplxdm>");//
		sb.append("\r\n");
		sb.append("<fpqqlsh>"+kpxx.getFpqqlsh()+"</fpqqlsh>");
		sb.append("\r\n");
		sb.append("<kplx>"+kpxx.getKplx()+"</kplx>");
		sb.append("\r\n");
		sb.append("<tspz>00</tspz>");
		sb.append("\r\n");
		sb.append("<xhdwsbh>"+kpxx.getXsfnsrsbh()+"</xhdwsbh>");
		sb.append("\r\n");
		sb.append("<xhdwmc>"+kpxx.getXsfmc()+"</xhdwmc>");
		sb.append("\r\n");
		sb.append("<xhdwdzdh>"+kpxx.getXsfdz() + kpxx.getXsfdh()+"</xhdwdzdh>");
		sb.append("\r\n");
		sb.append("<xhdwyhzh>"+kpxx.getGmfyhzh()+"</xhdwyhzh>");
		sb.append("\r\n");
		sb.append("<ghdwsbh>"+ kpxx.getGmfnsrsbh()+"</ghdwsbh>");
		sb.append("\r\n");
		sb.append("<ghdwmc>"+kpxx.getGmfmc()+"</ghdwmc>");
		sb.append("\r\n");
		sb.append("<ghdwdzdh>"+kpxx.getGmfdz() + kpxx.getGmfdh()+"</ghdwdzdh>");
		sb.append("\r\n");
		sb.append("<ghdwyhzh>"+kpxx.getGmfyhzh()+"</ghdwyhzh>");
		
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
				sb.append("<fphxz>"+(temp.getFphxz()==true?1:0)+"</fphxz>");
				sb.append("\r\n");
				sb.append("<spmc>"+temp.getXmmc()+"</spmc>");
				sb.append("\r\n");
				sb.append("<spsm></spsm>");
				sb.append("\r\n");
				sb.append("<ggxh>"+temp.getGgxh()+"</ggxh>");
				sb.append("\r\n");
				sb.append("<dw>"+temp.getDw()+"</dw>");
				sb.append("\r\n");
				sb.append("<spsl>"+temp.getXmsl()+"</spsl>");
				sb.append("\r\n");
				sb.append("<dj>"+ temp.getXmdj()+"</dj>");
				sb.append("\r\n");
				sb.append("<je>"+ temp.getXmje()+"</je>");
				sb.append("\r\n");
				sb.append("<sl>"+ temp.getSl()+"</sl>");
				sb.append("\r\n");
				sb.append("<se>"+ temp.getSe()+"</se>");
				sb.append("\r\n");
				sb.append("<hsbz>"+(temp.getHsbz()==true?1:0)+"</hsbz>");
				sb.append("\r\n");
				sb.append("</group>");
				sb.append("\r\n");
			}
			sb.append("</fyxm>");
		}
		
		sb.append("\r\n");
		sb.append("<hjje>"+ kpxx.getHjje() +"</hjje>");
		sb.append("\r\n");
		sb.append("<hjse>"+ kpxx.getHjse()+"</hjse>");
		sb.append("\r\n");
		sb.append("<jshj>"+ kpxx.getJshj() +"</jshj>");
		sb.append("\r\n");
		sb.append("<bz>"+kpxx.getBz()+"</bz>");
		sb.append("\r\n");
		sb.append("<skr>"+ kpxx.getSkr() +"</skr>");
		sb.append("\r\n");
		sb.append("<fhr>"+ kpxx.getFhr() +"</fhr>");
		sb.append("\r\n");
		sb.append("<kpr>"+ kpxx.getKpr() +"</kpr>");
		sb.append("\r\n");
		sb.append("<tzdbh></tzdbh>");
		sb.append("\r\n");
		sb.append("<yfpdm>"+ kpxx.getYfpdm()+"</yfpdm>");
		sb.append("\r\n");
		sb.append("<yfphm>"+ kpxx.getYfphm() +"</yfphm>");
		sb.append("\r\n");
		sb.append("<qmcs></qmcs>");
		sb.append("\r\n");
		sb.append("</body>");
		sb.append("\r\n");
		sb.append("</business>");
		return sb.toString();
	}
	
	public static String toPlainInvoice(Kpxx kpxx, List<Fpmx> fpmxList ,String kpzdbs) throws UnsupportedEncodingException{
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
		sb.append("<kpzdbs>"+kpzdbs+"</kpzdbs>");//
		sb.append("\r\n");
		sb.append("<fplxdm>"+kpxx.getFplx()+"</fplxdm>");//
		sb.append("\r\n");
		sb.append("<fpqqlsh>"+kpxx.getFpqqlsh()+"</fpqqlsh>");
		sb.append("\r\n");
		sb.append("<kplx>"+kpxx.getKplx()+"</kplx>");
		sb.append("\r\n");
		sb.append("<tspz>00</tspz>");
		sb.append("\r\n");
		sb.append("<sjh>"+kpxx.getSjh()+"</sjh>");
		sb.append("\r\n");
		sb.append("<xhdwsbh>"+kpxx.getXsfnsrsbh()+"</xhdwsbh>");
		sb.append("\r\n");
		sb.append("<xhdwmc>"+kpxx.getXsfmc()+"</xhdwmc>");
		sb.append("\r\n");
		sb.append("<xhdwdzdh>"+kpxx.getXsfdz() + kpxx.getXsfdh()+"</xhdwdzdh>");
		sb.append("\r\n");
		sb.append("<xhdwyhzh>"+kpxx.getGmfyhzh()+"</xhdwyhzh>");
		sb.append("\r\n");
		sb.append("<ghdwsbh>"+ kpxx.getGmfnsrsbh()+"</ghdwsbh>");
		sb.append("\r\n");
		sb.append("<ghdwmc>"+kpxx.getGmfmc()+"</ghdwmc>");
		sb.append("\r\n");
		sb.append("<ghdwdzdh>"+kpxx.getGmfdz() + kpxx.getGmfdh()+"</ghdwdzdh>");
		sb.append("\r\n");
		sb.append("<ghdwyhzh>"+kpxx.getGmfyhzh()+"</ghdwyhzh>");
		
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
				sb.append("<fphxz>"+(temp.getFphxz()==true?1:0)+"</fphxz>");
				sb.append("\r\n");
				sb.append("<spmc>"+temp.getXmmc()+"</spmc>");
				sb.append("\r\n");
				sb.append("<spsm></spsm>");
				sb.append("\r\n");
				sb.append("<ggxh>"+temp.getGgxh()+"</ggxh>");
				sb.append("\r\n");
				sb.append("<dw>"+temp.getDw()+"</dw>");
				sb.append("\r\n");
				sb.append("<spsl>"+temp.getXmsl()+"</spsl>");
				sb.append("\r\n");
				sb.append("<dj>"+ temp.getXmdj()+"</dj>");
				sb.append("\r\n");
				sb.append("<je>"+ temp.getXmje()+"</je>");
				sb.append("\r\n");
				sb.append("<sl>"+ temp.getSl()+"</sl>");
				sb.append("\r\n");
				sb.append("<se>"+ temp.getSe()+"</se>");
				sb.append("\r\n");
				sb.append("<hsbz>"+(temp.getHsbz()==true?1:0)+"</hsbz>");
				sb.append("\r\n");
				sb.append("</group>");
				sb.append("\r\n");
			}
			sb.append("</fyxm>");
		}
		
		sb.append("\r\n");
		sb.append("<hjje>"+ kpxx.getHjje() +"</hjje>");
		sb.append("\r\n");
		sb.append("<hjse>"+ kpxx.getHjse()+"</hjse>");
		sb.append("\r\n");
		sb.append("<jshj>"+ kpxx.getJshj()+"</jshj>");
		sb.append("\r\n");
		sb.append("<bz>"+kpxx.getBz()+"</bz>");
		sb.append("\r\n");
		sb.append("<skr>"+ kpxx.getSkr() +"</skr>");
		sb.append("\r\n");
		sb.append("<fhr>"+ kpxx.getFhr() +"</fhr>");
		sb.append("\r\n");
		sb.append("<kpr>"+ kpxx.getKpr() +"</kpr>");
		sb.append("\r\n");
		sb.append("<tzdbh></tzdbh>");
		sb.append("\r\n");
		sb.append("<yfpdm>"+ kpxx.getYfpdm() +"</yfpdm>");
		sb.append("\r\n");
		sb.append("<yfphm>"+ kpxx.getYfphm() +"</yfphm>");
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
		sb.append("<KPLX>"+kpxx.getKplx()+"</KPLX>");
		sb.append("\r\n");
		sb.append("<XSF_NSRSBH>"+kpxx.getXsfnsrsbh()+"</XSF_NSRSBH>");
		sb.append("\r\n");
		sb.append("<XSF_MC>"+kpxx.getXsfmc()+"</XSF_MC>");
		sb.append("\r\n");
		sb.append("<XSF_DZDH>"+kpxx.getXsfdz() +""+ kpxx.getXsfdh()+"</XSF_DZDH>");
		sb.append("\r\n");
		sb.append("<XSF_YHZH>"+kpxx.getXsfyhzh()+"</XSF_YHZH>");
		sb.append("\r\n");
		sb.append("<GMF_NSRSBH>"+ kpxx.getGmfnsrsbh()+"</GMF_NSRSBH>");
		sb.append("\r\n");
		sb.append("<GMF_MC>"+kpxx.getGmfmc()+"</GMF_MC>");
		sb.append("\r\n");
		sb.append("<GMF_DZDH>"+kpxx.getGmfdz() + kpxx.getGmfdh()+"</GMF_DZDH>");
		sb.append("\r\n");
		sb.append("<GMF_YHZH>"+kpxx.getGmfyhzh()+"</GMF_YHZH>");
		sb.append("\r\n");
		sb.append("<GMF_YX>"+kpxx.getYx()+"</GMF_YX>");
		sb.append("\r\n");
		sb.append("<GMF_SJH>"+kpxx.getSjh()+"</GMF_SJH>");
		sb.append("\r\n");
		sb.append("<KPR>"+ kpxx.getKpr() +"</KPR>");
		sb.append("\r\n");
		sb.append("<SKR>"+ kpxx.getSkr() +"</SKR>");
		sb.append("\r\n");
		sb.append("<FHR>"+ kpxx.getFhr() +"</FHR>");
		sb.append("\r\n");
		sb.append("<YFP_DM>"+ kpxx.getYfpdm() +"</YFP_DM>");
		sb.append("\r\n");
		sb.append("<YFP_HM>"+ kpxx.getYfphm() +"</YFP_HM>");
		sb.append("\r\n");
		sb.append("<HJJE>"+ kpxx.getHjje() +"</HJJE>");
		sb.append("\r\n");
		sb.append("<HJSE>"+ kpxx.getHjse()+"</HJSE>");
		sb.append("\r\n");
		sb.append("<JSHJ>"+ kpxx.getJshj() +"</JSHJ>");
		sb.append("\r\n");
		sb.append("<BZ>"+kpxx.getBz()+"</BZ>");
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
				sb.append("<FPHXZ>"+(temp.getFphxz()==true?1:0)+"</FPHXZ>");
				sb.append("\r\n");
				sb.append("<XMMC>"+temp.getXmmc()+"</XMMC>");
				sb.append("\r\n");
				sb.append("<GGXH>"+temp.getGgxh()+"</GGXH>");
				sb.append("\r\n");
				sb.append("<DW>"+temp.getDw()+"</DW>");
				sb.append("\r\n");
				sb.append("<XMSL>"+temp.getXmsl()+"</XMSL>");
				sb.append("\r\n");
				sb.append("<XMDJ>"+ temp.getXmdj()+"</XMDJ>");
				sb.append("\r\n");
				sb.append("<XMJE>"+ temp.getXmje()+"</XMJE>");
				sb.append("\r\n");
				sb.append("<SL>"+ temp.getSl()+"</SL>");
				sb.append("\r\n");
				sb.append("<SE>"+ temp.getSe()+"</SE>");
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
	
	public static byte[] convert(byte[] ubl){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    //File xsltFile = new File("src/main/java/com/tan/controller/ubl_ofd.xsl");
		InputStream is = XmlUtil.class.getResourceAsStream("xml_change.xsl");
	    Source xsltSource = new StreamSource(is);

	    Source xmlSource = new StreamSource(new ByteArrayInputStream(ubl));
	    TransformerFactory transFact = TransformerFactory.newInstance();
	    Transformer trans = null;
	    byte[] result = null;
	    try {
	        trans = transFact.newTransformer(xsltSource);
	        trans.transform(xmlSource, new StreamResult(baos));
	        result = baos.toByteArray();
	    } catch (TransformerConfigurationException e) {
	        e.printStackTrace();
	    } catch (TransformerException e) {
	        e.printStackTrace();
	    }finally {
	    	try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	   
	    return result;
	}
	
	public static String returnXml(Kpxx kpxx) throws UnsupportedEncodingException{
		if (null == kpxx ) {
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
		sb.append("<JQBH>"+kpxx.getJqbh()+"</JQBH>");//
		sb.append("\r\n");
		sb.append("<FP_DM>"+kpxx.getFpdm()+"</FP_DM>");
		sb.append("\r\n");
		sb.append("<FP_HM>"+kpxx.getFphm()+"</FP_HM>");
		sb.append("\r\n");
		sb.append("<KPRQ>"+kpxx.getKprq()+"</KPRQ>");
		sb.append("\r\n");
		sb.append("<FP_MW>"+kpxx.getSkm()+"</FP_MW>");
		sb.append("\r\n");
		sb.append("<JYM>"+kpxx.getJym()+"</JYM>");
		sb.append("\r\n");
		sb.append("<EWM>"+kpxx.getEwm()+"</EWM>");
		sb.append("\r\n");
		sb.append("<RETURNCODE>"+kpxx.getResultcode()+"</RETURNCODE>");
		sb.append("\r\n");
		sb.append("<RETURNMSG>"+kpxx.getResultmsg()+"</RETURNMSG>");
		sb.append("\r\n");
		sb.append("</RESPONSE_COMMON_FPKJ>");
		sb.append("\r\n");
		sb.append("</business>");

		return sb.toString();
	}
	
	public static void main(String[] args) {
			  String str="";
			  for(int i=0;i<20;i++){
			   str+=String.valueOf((ThreadLocalRandom.current().nextInt(0, 9)));
			  }
			  System.out.println(str);
	}
}
