package com.baiwang.einvoice.qz.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.dao.UserMapper;
import com.baiwang.einvoice.qz.service.IUserService;
import com.baiwang.einvoice.qz.service.PageServiceImpl;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper dao;
	
	@Resource
	private PageServiceImpl pageService;

	public User getUserByName(String name) {
		
		return dao.getUserByName(name);
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
	public int selectUserByPass(int userid, String pass) {
		
		// TODO Auto-generated method stub
		return dao.selectUserByPass(userid, pass);
		
	}

	/**
	  * <p>Title: changePass</p>
	  * <p>Description: </p>
	  * @param id
	  * @param user_pass
	  * @see com.baiwang.einvoice.qz.service.IUserService#changePass(int, java.lang.String)
	  */
	@Override
	public void changePass(int id, String user_pass) {
		
		// TODO Auto-generated method stub
		dao.changePass(id, user_pass);
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
	public User selectById(int id) {
		
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
