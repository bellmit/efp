package com.baiwang.einvoice.qz.service;

import java.util.HashMap;
import java.util.List;

import com.baiwang.einvoice.qz.beans.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface IUserService {

	public User getUserByName(String name);
	
	int selectUserByPass(String userid,String pass);
	
	User selectById(String id);
	
	void updateById(User user);
	
	PageList<HashMap<String, Object>> listUser(HashMap<String, String> param, int pageIndex, int pageSize);
	
	List<String> getNsrsbh();
}
