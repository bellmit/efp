package com.baiwang.einvoice.qz.dao;

import org.apache.ibatis.annotations.Param;

import com.baiwang.einvoice.qz.beans.ResultOfKp;

public interface ResultOfKpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResultOfKp record);

    int insertSelective(ResultOfKp record);

    ResultOfKp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResultOfKp record);

    int updateByPrimaryKey(ResultOfKp record);

	ResultOfKp queryResult(@Param("ddhm") String ddhm);

	int save(ResultOfKp result);

	int selectByDdhm(@Param("ddhm") String ddhm);

	int updateByDdhm(ResultOfKp result);
}