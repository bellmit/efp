package com.baiwang.einvoice.qz.dao;

import org.apache.ibatis.annotations.Param;

import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.ResultOfKp;

public interface ResultOfKpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResultOfKp record);

    int insertSelective(ResultOfKp record);

    ResultOfKp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResultOfKp record);

    int updateByPrimaryKey(ResultOfKp record);

	ResultOfKp queryResult2(@Param("ddhm")String ddhm, @Param("correlationId")String correlationId);

	int save(ResultOfKp result);

	int selectByDdhm(@Param("ddhm") String ddhm);

	int updateByDdhm(ResultOfKp result);

	/**
	 * 进入前置先根据两个订单号、发票类型查询结果
	 */
	Kpxx queryResult(@Param("zddh")String zddh, @Param("fddh")String fddh, @Param("fplx")String fplx);
}