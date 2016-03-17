package com.baiwang.einvoice.qz.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
		JdbcTemplate jdbcTemplate = new JdbcTemplate(loginDataSource);
		String sql = "select yhkl,qybz,yhlx,a.kpddm,b.nsrsbh, a.cjrdm from dj_czyxx a " + 
				"LEFT JOIN dj_kpdxx b " + 
				"ON a.KPDDM = b.KPDDM " + 
				" where a.czydm  = ?";
		logger.info("用户名【" + name + "】正在登录...");
		user = (User) jdbcTemplate.queryForObject(sql, new Object[] { name }, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String yhkl = rs.getString(1);
				String qybz = rs.getString(2);
				String yhlx = rs.getString(3);
				String kpddm = rs.getString(4);
				String nsrsbh = rs.getString(5);
				String cjrdm = rs.getString(6);
				User user1 = new User();
				user1.setYhkl(yhkl);
				user1.setQybz(qybz);
				user1.setYhlx(yhlx);
				user1.setKpddm(kpddm);
				user1.setNsrsbh(nsrsbh);
				user1.setCjrdm(cjrdm);
				return user1;
			}

		});
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
