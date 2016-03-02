package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.Kpxx;

public interface KpxxMapper {
    int deleteByPrimaryKey(Integer fpqqlsh);

    int insert(Kpxx record);

    int insertSelective(Kpxx record);

    Kpxx selectByPrimaryKey(Integer fpqqlsh);

    int updateByPrimaryKeySelective(Kpxx record);

    int updateByPrimaryKey(Kpxx record);
}