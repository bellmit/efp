/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baiwang.einvoice.qz.beans.ReportDetail;



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
	public static HSSFWorkbook  exportExcel(List<ReportDetail> reportList) throws Exception{
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
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell = row.createCell(1);
		cell.setCellValue("订单号");
		cell = row.createCell(2);
		cell.setCellValue("申请人");
		cell = row.createCell(3);
		cell.setCellValue("会员名");
		cell = row.createCell(4);
		cell.setCellValue("会员ID");
		cell = row.createCell(5);
		cell.setCellValue("订单时间");
		cell = row.createCell(6);
		cell.setCellValue("申请时间");
		cell = row.createCell(7);
		cell.setCellValue("发票抬头");
		cell = row.createCell(8);
		cell.setCellValue("发票类型");
		cell = row.createCell(9);
		cell.setCellValue("发票种类");
		cell = row.createCell(10);
		cell.setCellValue("申请入口");
		cell = row.createCell(11);
		cell.setCellValue("金额");
		cell = row.createCell(12);
		cell.setCellValue("收货人");
		cell = row.createCell(13);
		cell.setCellValue("收货人电话");
		cell = row.createCell(14);
		cell.setCellValue("寄送地址");
		cell = row.createCell(15);
		cell.setCellValue("邮寄时间");
		cell = row.createCell(16);
		cell.setCellValue("发票号码");
		cell = row.createCell(17);
		cell.setCellValue("发货人");
		cell = row.createCell(18);
		cell.setCellValue("物流公司");
		cell = row.createCell(19);
		cell.setCellValue("物流单号");
		cell = row.createCell(20);
		cell.setCellValue("退款状态");
		cell = row.createCell(21);
		cell.setCellValue("发票状态");
		
		for(int i=0 ; i<reportList.size() ; i++){
			ReportDetail  report = reportList.get(i) ;
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(i+1);  //序号
			cell = row.createCell(1);
			cell.setCellValue(report.getDdh());  //订单号
			cell = row.createCell(2);
			cell.setCellValue(report.getSqr());  //申请人
			cell = row.createCell(3);
			cell.setCellValue(report.getHym());  //会员名
			cell = row.createCell(4);
			cell.setCellValue(report.getHyid()); //会员ID
			cell = row.createCell(5);
			cell.setCellValue(report.getDdsj()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getDdsj()):"");    //订单时间
			cell = row.createCell(6);
			cell.setCellValue(report.getSqsj()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getSqsj()):"");   //申请时间
			cell = row.createCell(7);
			cell.setCellValue(report.getFptt());  //发票抬头
			cell = row.createCell(8);
			cell.setCellValue(report.getFplx());  //发票类型
			cell = row.createCell(9);
			if("004".equals(report.getFpzl())){
				cell.setCellValue("增值税专用发票");  //发票种类
			}else if ("007".equals(report.getFpzl())){
				cell.setCellValue("增值税普通发票");  //发票种类
			}else if ("026".equals(report.getFpzl())){
				cell.setCellValue("增值税电子发票");  //发票种类
			}
			cell = row.createCell(10);
			cell.setCellValue(report.getSqrk());  //申请入口
			cell = row.createCell(11);
			cell.setCellValue(report.getJe()!=null?report.getJe().toString():"");//金额
			cell = row.createCell(12);
			cell.setCellValue(report.getShr());//收货人
			cell = row.createCell(13);
			cell.setCellValue(report.getShrdh());//收货人电话
			cell = row.createCell(14);
			cell.setCellValue(report.getJsdz());//寄送地址
			cell = row.createCell(15);
			cell.setCellValue(report.getYjsj()!=null?new SimpleDateFormat("yyyy-MM-dd").format(report.getYjsj()):"");//邮寄时间
			cell = row.createCell(16);
			cell.setCellValue(report.getFphm());//发票号码
			cell = row.createCell(17);
			cell.setCellValue(report.getFhr());//发货人
			cell = row.createCell(18);
			cell.setCellValue(report.getWlgs());//物流公司 
			cell = row.createCell(19);
			cell.setCellValue(report.getWldh());//物流单号
			cell = row.createCell(20);
			cell.setCellValue(report.getTkzt());//退款状态
			cell = row.createCell(21);
			cell.setCellValue(report.getFpzt());//发票状态
			
		}
//		FileOutputStream fos = new FileOutputStream(excelpath);
//		wb.write(fos);
//		fos.close();
		return wb;
}
}
