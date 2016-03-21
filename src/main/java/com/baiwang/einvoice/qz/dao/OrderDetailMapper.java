package com.baiwang.einvoice.qz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiwang.einvoice.qz.beans.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByFpqqlsh(String fpqqlsh);
    
    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);
    
    int insertFromList(List<OrderDetail> orderDetails);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
    
    List<Map<String, String>> getPlainList(HashMap<String, String> param);
    
    List<Map<String, String>> getSpecialList(HashMap<String, String> param);
    
    List<Map<String, Object>> getPlainList4zf(Map<String, Object> param);
    
    List<Map<String, Object>> getSpecialList4zf(Map<String, Object> param);
    
    int getPlainList4zfCount(Map<String, Object> param);
    
    int getSpecialList4zfCount(Map<String, Object> param);
    
    List<Map<String, Object>> getPlainList4ch(Map<String, Object> param);
    
    List<Map<String, Object>> getSpecialList4ch(Map<String, Object> param);
    
    int getPlainList4chCount(Map<String, Object> param);
    
    int getSpecialList4chCount(Map<String, Object> param);
    
    OrderDetail selectByDdh(@Param("zddh")String zddh , @Param("fddh")String fddh);
}