package com.baiwang.einvoice.qz.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiwang.einvoice.qz.beans.Kpxx;

public interface ResultOfSkMapper {

	Integer saveResultOfSk(Map<String, String> map);

	Kpxx queryResult(@Param("fpqqlsh")String fpqqlsh, @Param("fplx")String fplx);

}
