/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.beans.ReportTotal;



/**
  * @ClassName: ReportUtil
  * @Description: 统计报表工具类
  * @author Administrator
  * @date 2016年3月1日 下午1:13:20
  */
public class ReportUtil {
	/**
	  * @author Administrator
	  * @Description: 导出excel
	  * @param @param ehdiList
	  * @param @return
	  * @param @throws Exception  
	  * @return HSSFWorkbook  
	  * @throws
	  * @date 2016年3月4日 下午1:20:01
	  */
	public static HSSFWorkbook  exportExcel(List<ReportDetail> reportList,String fplx) throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();//excel文档
		HSSFSheet sheet = wb.createSheet("日常发票");//工作薄
/*		
		//设置样式
		HSSFCellStyle cellStyle1 = wb.createCellStyle();//红底蓝字
		 Font font1 =  wb.createFont();//蓝字
		 font1.setColor(HSSFColor.BLUE.index);
		 font1.setBoldweight(Font.BOLDWEIGHT_BOLD); 
		cellStyle1.setFont(font1);
		cellStyle1.setFillForegroundColor((short)10);
		cellStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle1.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle1.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle1.setBorderRight((short)1);
		cellStyle1.setBorderBottom((short)1);
		
		HSSFCellStyle cellStyle2 = wb.createCellStyle();//黄底蓝字
		cellStyle2.setFont(font1);
		cellStyle2.setFillForegroundColor((short)5);
		cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle2.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle2.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle2.setBorderRight((short)1);
		cellStyle2.setBorderBottom((short)1);
		
		HSSFCellStyle cellStyle3 = wb.createCellStyle();//浅蓝底黑字
		Font font2 = wb.createFont();
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle3.setFont(font2);
		cellStyle3.setFillForegroundColor((short)12);
		cellStyle3.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle3.setRightBorderColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle3.setBottomBorderColor(HSSFColor.LIGHT_GREEN.index);
		cellStyle3.setBorderRight((short)1);
		cellStyle3.setBorderBottom((short)1);
		
		//设置sheet抬头样式
		HSSFCellStyle cellStyle = wb.createCellStyle();//墨绿底蓝字
		 Font font =  wb.createFont();
		 font.setColor(HSSFColor.BLUE.index);//蓝字
		 font.setBoldweight(Font.BOLDWEIGHT_BOLD); 
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor((short)50);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderRight((short)1);
		cellStyle.setBorderBottom((short)1);
		
		//特殊数据标红
		HSSFCellStyle cellStyle4Red = wb.createCellStyle();
		 Font font4red =  wb.createFont();
		 font4red.setColor(HSSFColor.RED.index);
		 cellStyle4Red.setFont(font4red);
		 
		//生成EXCEL下拉列表(开始)
			String[] textlist={
					 "0-迟到早退","1-现场储备","2-回公司开会","3-离场","4-公司储备","5-打卡异常","6-闲置","7-正常倒休","8-重复工时追加倒休"
					};
			String[] textlist1={"工作登记或加班单未审批","转项目","系统问题","其他问题"};
			
			CellRangeAddressList regions = new CellRangeAddressList(1,ehdiList.size(),27,27);
			CellRangeAddressList regions1 = new CellRangeAddressList(1,ehdiList.size(),30,30);
			
			//生成下拉框内容   
			DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
			DVConstraint constraint1 = DVConstraint.createExplicitListConstraint(textlist1);
			//绑定下拉框和作用区域   
			HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);     
			HSSFDataValidation data_validation1 = new HSSFDataValidation(regions1,constraint1);     
			//对sheet页生效   
			sheet.addValidationData(data_validation);
			sheet.addValidationData(data_validation1);
			
			//生成EXCEL下拉列表(结束)
		 
*/		
		HSSFRow  row = sheet.createRow(0);
		int a = 0;
		HSSFCell cell = row.createCell(a++);
		cell.setCellValue("序号");
		cell = row.createCell(a++);
		cell.setCellValue("流水号");
		cell = row.createCell(a++);
		cell.setCellValue("订单号");
		cell = row.createCell(a++);
		cell.setCellValue("学员ID");
		cell = row.createCell(a++);
		cell.setCellValue("地区");
		cell = row.createCell(a++);
		cell.setCellValue("订单时间");
		cell = row.createCell(a++);
		cell.setCellValue("付款时间");
		cell = row.createCell(a++);
		cell.setCellValue("发票申请时间");
		cell = row.createCell(a++);
		cell.setCellValue("发票抬头");
		cell = row.createCell(a++);
		cell.setCellValue("发票内容");
		cell = row.createCell(a++);
		cell.setCellValue("发票备注");
		cell = row.createCell(a++);
		cell.setCellValue("发票种类");
		cell = row.createCell(a++);
		cell.setCellValue("申请入口");
		if("004".equals(fplx)){
			cell = row.createCell(a++);
			cell.setCellValue("购方纳税人识别号");
			cell = row.createCell(a++);
			cell.setCellValue("购方地址、电话");
			cell = row.createCell(a++);
			cell.setCellValue("购方开户行及账号");
		}
		cell = row.createCell(a++);
		cell.setCellValue("发票金额");
		cell = row.createCell(a++);
		cell.setCellValue("收货人");
		cell = row.createCell(a++);
		cell.setCellValue("收货人电话");
		cell = row.createCell(a++);
		cell.setCellValue("寄送地址");
		cell = row.createCell(a++);
		cell.setCellValue("发票号码");
		cell = row.createCell(a++);
		cell.setCellValue("发货人");
		cell = row.createCell(a++);
		cell.setCellValue("物流公司");
		cell = row.createCell(a++);
		cell.setCellValue("物流单号");
		cell = row.createCell(a++);
		cell.setCellValue("邮寄时间");
		
		for(int i=0 ; i<reportList.size() ; i++){
			ReportDetail  report = reportList.get(i) ;
			row = sheet.createRow(i+1);
			int b = 0;
			cell = row.createCell(b++);
			cell.setCellValue(i+1);  //序号
			cell = row.createCell(b++);
			cell.setCellValue(report.getFpqqlsh());  //流水号
			cell = row.createCell(b++);
			cell.setCellValue(report.getDdh()); //订单号
			cell = row.createCell(b++);
			cell.setCellValue(report.getHyid()); //学员ID
			cell = row.createCell(b++);
			if("00".equals(report.getFpdq())){
				cell.setCellValue("北京");  //发票地区
			}else if("01".equals(report.getFpdq())){
				cell.setCellValue("上海");  //发票地区
			}
			cell = row.createCell(b++);
			cell.setCellValue(report.getDdsj()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getDdsj()):"");    //订单时间
			cell = row.createCell(b++);
			cell.setCellValue(report.getFksj()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getDdsj()):"");    //付款时间
			cell = row.createCell(b++);
			cell.setCellValue(report.getSqsj()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getSqsj()):"");   //申请时间
			cell = row.createCell(b++);
			cell.setCellValue(report.getFptt());  //发票抬头
			cell = row.createCell(b++);
			cell.setCellValue(report.getXmmc());  //发票内容
			cell = row.createCell(b++);
			cell.setCellValue(report.getBzfp());  //发票备注
			cell = row.createCell(b++);
			if("004".equals(report.getFplx())){
				cell.setCellValue("专票");  //发票种类
			}else if ("007".equals(report.getFplx())){
				cell.setCellValue("普票");  //发票种类
			}else if ("026".equals(report.getFplx())){
				cell.setCellValue("电子发票");  //发票种类
			}
			cell = row.createCell(b++);
			if("00".equals(report.getSqrk())){
				cell.setCellValue("前台");  //申请入口
			}else if ("01".equals(report.getSqrk())){
				cell.setCellValue("后台");  //申请入口
			}
			if("004".equals(fplx)){
				cell = row.createCell(b++);
				cell.setCellValue(report.getGmfnsrsbh());  //购方纳税人识别号
				cell = row.createCell(b++);
				cell.setCellValue(report.getGmfdz()+report.getGmfdh());  //购方地址、电话
				cell = row.createCell(b++);
				cell.setCellValue(report.getGmfyhzh());  //购方开户行及账号
			}
			cell = row.createCell(b++);
			cell.setCellValue(report.getHjje()!=null?report.getHjje().toString():"");//发票金额
			cell = row.createCell(b++);
			cell.setCellValue(report.getShr());//收货人
			cell = row.createCell(b++);
			cell.setCellValue(report.getShrdh());//收货人电话
			cell = row.createCell(b++);
			cell.setCellValue(report.getJsdz());//寄送地址
			cell = row.createCell(b++);
			cell.setCellValue(report.getFphm());//发票号码
			cell = row.createCell(b++);
			cell.setCellValue(report.getFhr());//发货人
			cell = row.createCell(b++);
			cell.setCellValue(report.getWlgs());//物流公司 
			cell = row.createCell(b++);
			cell.setCellValue(report.getWldh());//物流单号
			cell = row.createCell(b++);
			cell.setCellValue(report.getYjsj()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getYjsj()):"");//邮寄时间
		}
//		FileOutputStream fos = new FileOutputStream(excelpath);
//		wb.write(fos);
//		fos.close();
		return wb;
}
	
	/**
	  * @author ldm
	  * @Description: 导出统计信息excel
	  * @param @param ehdiList
	  * @param @return
	  * @param @throws Exception  
	  * @return HSSFWorkbook  
	  * @throws
	  * @date 2016年3月4日 下午1:20:01
	  */
	public static HSSFWorkbook  exportExcel_stat(List<ReportTotal> reportList) throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();//excel文档
		HSSFSheet sheet = wb.createSheet("发票统计");//工作薄
		HSSFRow  row = sheet.createRow(0);
		int a =0;
		HSSFCell cell = row.createCell(a++);
		cell.setCellValue("发票地区");
		cell = row.createCell(a++);
		cell.setCellValue("发票客户");
		cell = row.createCell(a++);
		cell.setCellValue("开票日期");
		cell = row.createCell(a++);
		cell.setCellValue("发票种类");
		cell = row.createCell(a++);
		cell.setCellValue("发票代码");
		cell = row.createCell(a++);
		cell.setCellValue("发票号码");
		cell = row.createCell(a++);
		cell.setCellValue("抬头类型");
		cell = row.createCell(a++);
		cell.setCellValue("购方名称");
		cell = row.createCell(a++);
		cell.setCellValue("发票内容");
		cell = row.createCell(a++);
		cell.setCellValue("备注");
		cell = row.createCell(a++);
		cell.setCellValue("报送状态");
		cell = row.createCell(a++);
		cell.setCellValue("发票状态");
		cell = row.createCell(a++);
		cell.setCellValue("合计金额");
		cell = row.createCell(a++);
		cell.setCellValue("合计税额");
		cell = row.createCell(a++);
		cell.setCellValue("价税合计");
		
		for(int i=0 ; i<reportList.size() ; i++){
			ReportTotal  report = reportList.get(i) ;
			row = sheet.createRow(i+1);
			int b = 0;
			cell = row.createCell(b++);
			if("00".equals(report.getFpdq())){
				cell.setCellValue("北京");  //发票地区
			}else if("01".equals(report.getFpdq())){
				cell.setCellValue("上海");  //发票地区
			}
			cell = row.createCell(b++);
			if("00".equals(report.getFpkh())){
				cell.setCellValue("B2B");  //发票客户
			}else if("01".equals(report.getFpkh())){
				cell.setCellValue("B2C");  //发票客户
			}
			cell = row.createCell(b++);
			cell.setCellValue(report.getKprq()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getKprq()):"");  //开票日期
			cell = row.createCell(b++);
			if("004".equals(report.getFpzl())){
				cell.setCellValue("专票");  //发票种类
			}else if ("007".equals(report.getFpzl())){
				cell.setCellValue("普票");  //发票种类
			}else if ("026".equals(report.getFpzl())){
				cell.setCellValue("电子发票");  //发票种类
			}
			cell = row.createCell(b++);
			cell.setCellValue(report.getFpdm()); //发票代码
			cell = row.createCell(b++);
			cell.setCellValue(report.getFphm());   //发票号码
			cell = row.createCell(b++);
			if("00".equals(report.getTtlx())){
				cell.setCellValue("个人");  //抬头类型
			}else if("01".equals(report.getTtlx())){
				cell.setCellValue("公司");  //抬头类型
			}
			cell = row.createCell(b++);
			cell.setCellValue(report.getGfmc());  //购方名称
			cell = row.createCell(b++);
			cell.setCellValue(report.getFpnr());  //发票内容
			cell = row.createCell(b++);
			cell.setCellValue(report.getBz());  //备注
			cell = row.createCell(b++);
			cell.setCellValue(report.getBszt());//报送状态
			cell = row.createCell(b++);
			cell.setCellValue(report.getFpzt());//发票状态
			cell = row.createCell(b++);
			cell.setCellValue(report.getHjje());//合计金额
			cell = row.createCell(b++);
			cell.setCellValue(report.getHjse());//合计税额
			cell = row.createCell(b++);
			cell.setCellValue(report.getJshj());//价税合计
			
		}
		return wb;
}
}
