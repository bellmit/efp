package com.baiwang.einvoice.qz.dao;

import java.util.List;

import com.baiwang.einvoice.qz.beans.Fpmx;

public interface FpmxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fpmx record);

    int insertSelective(Fpmx record);

    Fpmx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fpmx record);

    int updateByPrimaryKey(Fpmx record);
    
    int insertFromList(List<Fpmx> list);
    
    int deleteByFpqqlsh(String id);
}