/**
 * 
 */
package com.baiwang.einvoice.qz.utils;

/**
 *
 * @project 百望股份电子发票前置平台
 * @package com.bwplat.eqz.qzadapter.invoice.util
 * @file InvoiceUtil.java 创建时间:2015年12月24日下午8:04:11
 * @title 发票开具工具类
 * @description 开具发票返回报文、截取字符串
 * @copyright Copyright (c) 2015 百望股份有限公司
 * @company 百望股份有限公司
 * @module 模块: 模块名称
 * @author   gkm
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
public class InvoiceUtil {
	
	private final static String msg1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			   +"<business id=\"FPKJ\" comment=\"发票开具\">"
			   +"<RESPONSE_COMMON_FPKJ class=\"RESPONSE_COMMON_FPKJ\">"
			   +"<FPQQLSH>";
	//发票请求流水号
	private final static String msg2 = "</FPQQLSH><JQBH>";
	//税控设备编号
	private final static String msg3 = "</JQBH><FP_DM>";
	//发票代码
	private final static String msg4 = "</FP_DM><FP_HM>";
	//发票号码
	private final static String msg5 = "</FP_HM><KPRQ>";
	//开票日期
	private final static String msg6 = "</KPRQ><RETURNCODE>";
	//返回代码
	private final static String msg7 = "</RETURNCODE><RETURNMSG>";
	//返回信息
	private final static String msg8 = "</RETURNMSG><FP_MW>";
	
	private final static String msg9 = "</FP_MW><JYM>";
	
	private final static String msg10 = "</JYM><EWM>";
	
	private final static String msg11 = "</EWM></RESPONSE_COMMON_FPKJ></business>";
	
	
	/**
	 * 
	 *@name    开具发票返回报文
	 *@description 相关说明
	 *"4006", "请求税控系统失败"
	 *"4007", "此发票已经开具过。"
	 *@time    创建时间:2015年12月25日上午10:38:43
	 *@param code
	 *@param msg
	 *@param xml
	 *@return
	 *@author   Administrator
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static String backMsg(String code, String msg, String xml){
		if(null == xml || "".equals(xml)){
			return msg1 + msg2 + msg3 + msg4 + msg5 + msg6 + "4000" + msg7 + "信息为空。" + msg8 + msg9 + msg10 + msg11;
		}
		
		return msg1 + InvoiceUtil.getIntervalValue(xml,"<FPQQLSH>","</FPQQLSH>") 
		+ msg2 + InvoiceUtil.getIntervalValue(xml,"<JQBH>","</JQBH>") + msg3 
		+ InvoiceUtil.getIntervalValue(xml,"<FP_DM>","</FP_DM>") + msg4 
		+ InvoiceUtil.getIntervalValue(xml,"<FP_HM>","</FP_HM>") + msg5 
		+ InvoiceUtil.getIntervalValue(xml,"<KPRQ>","</KPRQ>") + msg6 
		+ code + msg7 + msg + msg8
		+ InvoiceUtil.getIntervalValue(xml,"<FP_MW>","</FP_MW>") + msg9 
		+ InvoiceUtil.getIntervalValue(xml,"<JYM>","</JYM>") + msg10 
		+ InvoiceUtil.getIntervalValue(xml,"<EWM>","</EWM>") + msg11 
		;
		
	}
	
	
	public static String getIntervalValue(String source, String priStr, String suxStr){
		return getIntervalValue(source, priStr, 0, suxStr);
	}
	
	public static String getIntervalValue(String source, String priStr, int fromIndex, String suxStr){
    	if (source == null)
    		return "";
    	int iFirst = source.indexOf(priStr, fromIndex);
    	int iLast = source.indexOf(suxStr, fromIndex);
        if(iFirst < 0 || iLast < 0)
        	return "";
        int beginIndex = iFirst + priStr.length();
        return source.substring(beginIndex, iLast);
    }
	

}
