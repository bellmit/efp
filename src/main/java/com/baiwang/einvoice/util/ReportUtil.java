/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.util;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;


/**
  * @ClassName: ReportUtil
  * @Description: 统计报表工具类
  * @author Administrator
  * @date 2016年3月1日 下午1:13:20
  */
public class ReportUtil {
	public static void main(String[] args) {
		try {
			xls4Report();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.print("finish");
		}
	}
	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param jtURL jasper模板文件的保存路径
	  * @param @param data 数据集合
	  * @param @param flag html/xls
	  * @param @throws SQLException
	  * @param @throws JRException  
	  * @return void  
	  * @throws
	  * @date 2016年3月2日 下午3:35:06
	  */
	public static void xls4Report() throws SQLException, JRException {
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(new HashSet());
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject("jasperTemplate/wyenTotal.jasper");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("month", "111月");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),datasource);
		JRXlsExporter xlsExporter = new JRXlsExporter();
		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File("reports/wyen1.xls"));
		xlsExporter.exportReport();
		
	}
	public static void sql4Report() throws SQLException, JRException {
		// create the ResultSet
		Connection con = getConnection();
		PreparedStatement statement = con.prepareStatement("select * from kpxx ");
		ResultSet resultSet = statement.executeQuery();

		JRResultSetDataSource result = new JRResultSetDataSource(resultSet);
//		JasperReport jasperReport = JasperCompileManager.compileReport("jasperTemplate/wyenTotal.jrxml");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject("jasper/wyen.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), result);

		JasperExportManager.exportReportToPdfFile(jasperPrint, "jasperTemplate/wyenTotal.pdf");
	}
	
	public static void parameter4Report() throws SQLException, JRException {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("master_location_id", new BigDecimal(22));

		Connection con = getConnection();
//		JasperReport jasperReport = JasperCompileManager.compileReport("jasperTemplate/wyenTotal.jrxml");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject("jasper/wyen.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, con);

		JasperExportManager.exportReportToPdfFile(jasperPrint, "/jasperTemplate/wyenTotal.pdf");
	}

	public static void jasper4Report() throws SQLException, JRException {
		// create the ResultSet
		Connection con = getConnection();
		PreparedStatement statement = con.prepareStatement(
				"select * from test_user ");
		ResultSet resultSet = statement.executeQuery();

		JRResultSetDataSource result = new JRResultSetDataSource(resultSet);
		// modify
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject("jasper/wyen.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), result);

		JasperExportManager.exportReportToPdfFile(jasperPrint, "jasperTemplate/wyenTotal.pdf");
	}
	public static void xls4Report1() throws SQLException, JRException {
		Connection con = getConnection();
		PreparedStatement statement = con.prepareStatement(
				"select * from kpxx ");
		ResultSet resultSet = statement.executeQuery();
		JRResultSetDataSource result = new JRResultSetDataSource(resultSet);
		// create JasperReport from .jasper
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject("jasperTemplate/wyenTotal.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),result);

		JRXlsExporter xlsExporter = new JRXlsExporter();
		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File("reports/wyen.xls"));
		xlsExporter.exportReport();
	}
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pw);
	}

	private static String url = "jdbc:mysql://192.168.6.188:3306/einvoice?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String pw = "";
}
