package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.ResultOfKp;

public interface ResultOfKpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResultOfKp record);

    int insertSelective(ResultOfKp record);

    ResultOfKp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResultOfKp record);

    int updateByPrimaryKey(ResultOfKp record);
}