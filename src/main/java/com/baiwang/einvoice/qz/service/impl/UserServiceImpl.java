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

}
