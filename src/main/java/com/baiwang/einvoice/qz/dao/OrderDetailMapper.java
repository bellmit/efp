package com.baiwang.einvoice.qz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
    
    List<Map<String, String>> getPlainList(HashMap<String, String> param);
    
    List<Map<String, String>> getSpecialList(HashMap<String, String> param);
}