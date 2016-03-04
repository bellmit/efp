package com.baiwang.einvoice.qz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.dao.UserMapper;
import com.baiwang.einvoice.qz.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper dao;

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

}
