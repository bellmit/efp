package com.baiwang.einvoice.qz.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.mysql.fabric.xmlrpc.base.Array;

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
		String loginDburl ;
		String loginDbName ;
		String loginDbPassword ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(loginDataSource);
		String sql = "select a.CZYDM,a.CZYMC,yhkl,qybz,yhlx,a.kpddm,b.nsrsbh,a.cjrdm,group_concat(c.FPLXDM) as fplxdm from dj_czyxx a " + 
				"LEFT JOIN dj_kpdxx b " + 
				"ON a.KPDDM = b.KPDDM " + 
				"LEFT JOIN dj_fpcxx c " + 
				"ON b.KPDDM = c.KPDDM " + 
				"where a.czydm  =  ?  " + 
				"group by a.CZYDM,a.CZYMC,yhkl,qybz,yhlx,a.kpddm,b.nsrsbh,a.cjrdm";
		logger.info("用户名【" + name + "】正在登录...");
		user = (User) jdbcTemplate.queryForObject(sql, new Object[] { name }, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String czydm = rs.getString(1);
				String czymc = rs.getString(2);
				String yhkl = rs.getString(3);
				String qybz = rs.getString(4);
				String yhlx = rs.getString(5);
				String kpddm = rs.getString(6);
				String nsrsbh = rs.getString(7);
				String cjrdm = rs.getString(8);
				String fplxdm = rs.getString(9);
				User user1 = new User();
				user1.setYhkl(yhkl);
				user1.setQybz(qybz);
				user1.setYhlx(yhlx);
				user1.setKpddm(kpddm);
				user1.setNsrsbh(nsrsbh);
				user1.setCjrdm(cjrdm);
				user1.setFplxdm(fplxdm);
				user1.setCzydm(czydm);
				user1.setCzymc(czymc);
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

	/**
	  * <p>Title: getNsrsbh</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IUserService#getNsrsbh()
	  */
	@Override
	public List<String> getNsrsbh() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(loginDataSource);
		String sql = "select distinct(nsrsbh) from dj_fpcxx";
		logger.info("获取纳税人识别号列表");
		List<String> list = new ArrayList<>();
		list = jdbcTemplate.queryForList(sql,String.class);
		return list;
		
	}

}
