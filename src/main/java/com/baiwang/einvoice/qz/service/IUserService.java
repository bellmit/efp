package com.baiwang.einvoice.qz.service;

import java.util.HashMap;

import com.baiwang.einvoice.qz.beans.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface IUserService {

	public User getUserByName(String name);
	
	int selectUserByPass(int userid,String pass);
	
	User selectById(int id);
	
	void updateById(User user);
	
	void changePass(int id, String user_pass);
	
	PageList<HashMap<String, Object>> listUser(HashMap<String, String> param, int pageIndex, int pageSize);
}
