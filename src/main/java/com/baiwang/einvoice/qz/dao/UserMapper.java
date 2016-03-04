package com.baiwang.einvoice.qz.dao;

import org.apache.ibatis.annotations.Param;

import com.baiwang.einvoice.qz.beans.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User getUserByName(String name);
	
	int selectUserByPass(@Param("id")int id,@Param("user_pass")String user_pass);
	
	void changePass(@Param("id")int id,@Param("user_pass")String user_pass);
}