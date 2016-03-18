package com.baiwang.einvoice.qz.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.dao.UserMapper;
import com.baiwang.einvoice.qz.service.IUserService;
import com.baiwang.einvoice.qz.service.PageServiceImpl;
import com.baiwang.einvoice.qz.utils.JDBCUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Resource
	private DataSource loginDataSource;
	
	@Resource
	private UserMapper dao;
	
	@Resource
	private PageServiceImpl pageService;

	
	/**
	 * 
	 * @param name
	 * @return
	 * @modify 2016年3月16日15:04:48 zhaowei
	 */
	public User getUserByName(String name) {
		User user = new User();
		Connection conn = JDBCUtil.getConn();
		Statement stmt = null;
		ResultSet rs  = null;
		String sql = "select a.CZYDM,a.CZYMC,yhkl,qybz,yhlx,a.kpddm,b.nsrsbh,a.cjrdm,group_concat(c.FPLXDM) as fplxdm from dj_czyxx a " + 
				"LEFT JOIN dj_kpdxx b " + 
				"ON a.KPDDM = b.KPDDM " + 
				"LEFT JOIN dj_fpcxx c " + 
				"ON b.KPDDM = c.KPDDM " +  
				"where a.czydm  = '"+ name + "' "+
				"group by a.CZYDM,a.CZYMC,yhkl,qybz,yhlx,a.kpddm,b.nsrsbh,a.cjrdm";
		logger.info("用户名【" + name + "】正在登录...");
		 
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				String czydm = rs.getString(1);
				String czymc = rs.getString(2);
				String yhkl = rs.getString(3);
				String qybz = rs.getString(4);
				String yhlx = rs.getString(5);
				String kpddm = rs.getString(6);
				String nsrsbh = rs.getString(7);
				String cjrdm = rs.getString(8);
				String fplxdm = rs.getString(9);
				user.setYhkl(yhkl);
				user.setQybz(qybz);
				user.setYhlx(yhlx);
				user.setKpddm(kpddm);
				user.setNsrsbh(nsrsbh);
				user.setCjrdm(cjrdm);
				user.setFplxdm(fplxdm);
				user.setCzydm(czydm);
				user.setCzymc(czymc);
			}
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
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

	/**
	  * <p>Title: getNsrsbh</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IUserService#getNsrsbh()
	  */
	@Override
	public List<String> getNsrsbh() {
		Connection conn = JDBCUtil.getConn();
		Statement stmt = null;
		ResultSet result  = null;
        logger.info("获取纳税人识别号列表");
		List<String> list = new ArrayList<>();
        try {
        	stmt = conn.createStatement();
    		String sql = "select distinct(nsrsbh) from dj_fpcxx"; 
            result = stmt.executeQuery(sql);
			while(result.next()){
				list.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}

}
