package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.PrintConfig;

public interface PrintConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrintConfig record);

    int insertSelective(PrintConfig record);

    PrintConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrintConfig record);

    int updateByPrimaryKey(PrintConfig record);
    
    PrintConfig queryPrintsetting(String fplx);

	Integer savePrintsetting(PrintConfig printconfig);
}