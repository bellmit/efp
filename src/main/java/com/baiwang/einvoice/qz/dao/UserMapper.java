package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int selectUserByPass(String userId , String pass);
}