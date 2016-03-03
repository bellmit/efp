package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.Kpxx;

public interface KpxxMapper {
    int deleteByPrimaryKey(String fpqqlsh);

    int insert(Kpxx record);

    int insertSelective(Kpxx record);

    Kpxx selectByPrimaryKey(String fpqqlsh);

    int updateByPrimaryKeySelective(Kpxx record);

    int updateByPrimaryKey(Kpxx record);
    
    void deleteByDdhm(String ddhm);
}