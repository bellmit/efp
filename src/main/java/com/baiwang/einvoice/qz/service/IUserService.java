package com.baiwang.einvoice.qz.service;

import com.baiwang.einvoice.qz.beans.User;

public interface IUserService {

	public User getUserByName(String name);
	
	int selectUserByPass(int userid,String pass);
	
	void changePass(int id, String user_pass);
}
