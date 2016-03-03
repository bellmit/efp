package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.CustomOrder;

public interface CustomOrderMapper {
    int deleteByPrimaryKey(String ddhm);

    int insert(CustomOrder record);

    int insertSelective(CustomOrder record);

    CustomOrder selectByPrimaryKey(String ddhm);

    int updateByPrimaryKeySelective(CustomOrder record);

    int updateByPrimaryKey(CustomOrder record);
}