package com.baiwang.einvoice.qz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;

public interface KpxxMapper {
    int deleteByPrimaryKey(String fpqqlsh);

    int insert(Kpxx record);

    int insertSelective(Kpxx record);

    Kpxx selectByPrimaryKey(String fpqqlsh);
    
    Kpxx selectByFpqqlsh(String fpqqlsh);

    Kpxx selectByDdhm(@Param("zddh")String zddh , @Param("fddh")String fddh);
    
    int updateByPrimaryKeySelective(Kpxx record);

    int updateByPrimaryKey(Kpxx record);
    
    void deleteByDdhm(String ddhm);
    
    void updateFpztByFpqqlsh(String fpqqlsh);
    
    int zfByFpqqlsh(String fpqqlsh);
    
    List<Map<String, String>> getPlainList(HashMap<String, String> param);
    
    List<Map<String, String>> getSpecialList(HashMap<String, String> param);
    
    void saveCallBackInfo(Kpxx kpxx);
    
    void insertKpxx(Kpxx kpxx);
    
    void insertFpmx(Fpmx fpmx);
}