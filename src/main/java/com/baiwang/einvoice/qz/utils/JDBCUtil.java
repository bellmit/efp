/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
  * @ClassName: JDBCUtil
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月17日 下午9:10:02
  */
public class JDBCUtil {

	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = ConfigUtil.get("jdbc.loginDburl");
	private final static String username = ConfigUtil.get("jdbc.loginDbName");
	private final static String password = ConfigUtil.get("jdbc.loginDbPassword");
	
	public static Connection getConn() {

		Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
}
