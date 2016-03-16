package com.baiwang.einvoice.qz.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.dao.UserMapper;
import com.baiwang.einvoice.qz.service.IUserService;
import com.baiwang.einvoice.qz.service.PageServiceImpl;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Resource
	private UserMapper dao;
	
	@Resource
	private PageServiceImpl pageService;

	
	private static String loginDburl ;
	private static String loginDbName ;
	private static String loginDbPassword ;
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public User getUserByName(String name) {
		User user = new User();
		if(loginDburl==null){
			InputStream in;
			in=getClass().getClassLoader().getResourceAsStream("jdbc.properties"); 
			Properties   props   =  new  Properties();
			try {
				props.load(in);
				loginDburl = props.get("jdbc.loginDburl").toString();
				loginDbName = "user="+props.get("jdbc.loginDbName").toString();
				loginDbPassword = "&password="+props.get("jdbc.loginDbPassword").toString();
			} catch (IOException e1) {
				logger.error("///////获取数据库连接error///////////");
				e1.printStackTrace();
				return user;
			}
		}
		
		String url = loginDburl+loginDbName+loginDbPassword;
		try (Connection conn = DriverManager.getConnection(url);){
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            Statement stmt = conn.createStatement();
            String sql = "select yhkl,qybz,yhlx,kpddm,nsrsbh,cjrdm from dj_czyxx where czydm = '"+name+"'"; 
            ResultSet result = stmt.executeQuery(sql);
            if(result.first()){
            	String yhkl =  result.getString(1);
            	String qybz =  result.getString(2);
            	String yhlx =  result.getString(3);
            	String kpddm =  result.getString(4);
            	String nsrsbh =  result.getString(5);
            	String cjrdm = result.getString(6);
            	user.setYhkl(yhkl);
            	user.setQybz(qybz);
            	user.setYhlx(yhlx);
            	user.setKpddm(kpddm);
            	user.setNsrsbh(nsrsbh);
            	user.setCjrdm(cjrdm);
            }
        } catch (SQLException e) {
        	logger.error("///////JDBC连接数据库error///////////");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	logger.error("///////数据库驱动加载error///////////");
            e.printStackTrace();
		} 
		return user;
	}

	/**
	  * <p>Title: selectUserByPass</p>
	  * <p>Description: </p>
	  * @param userid
	  * @param pass
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IUserService#selectUserByPass(java.lang.String, java.lang.String)
	  */
	@Override
	public int selectUserByPass(String userid, String pass) {
		
		// TODO Auto-generated method stub
		return dao.selectUserByPass(userid, pass);
		
	}

	/**
	  * <p>Title: listUser</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IUserService#listUser()
	  */
	@SuppressWarnings("unchecked")
	@Override
	public PageList<HashMap<String, Object>> listUser(HashMap<String, String> param, int pageIndex, int pageSize) {
		
		// TODO Auto-generated method stub
		return (PageList<HashMap<String, Object>>) pageService.getPageList(UserMapper.class, "listUser",param, pageIndex, pageSize);
		
	}

	/**
	  * <p>Title: selectById</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IUserService#selectById(int)
	  */
	@Override
	public User selectById(String id) {
		
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
		
	}

	/**
	  * <p>Title: updateById</p>
	  * <p>Description: </p>
	  * @param id
	  * @see com.baiwang.einvoice.qz.service.IUserService#updateById(int)
	  */
	@Override
	public void updateById(User user) {
		
		// TODO Auto-generated method stub
		dao.updateByPrimaryKeySelective(user);
	}

}
