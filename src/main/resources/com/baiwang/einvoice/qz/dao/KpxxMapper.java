package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.Kpxx;

public interface KpxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Kpxx record);

    int insertSelective(Kpxx record);

    Kpxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Kpxx record);

    int updateByPrimaryKey(Kpxx record);
}