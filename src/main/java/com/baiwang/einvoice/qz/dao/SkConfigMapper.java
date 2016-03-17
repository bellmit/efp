package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.SkConfig;

public interface SkConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkConfig record);

    int insertSelective(SkConfig record);

    SkConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkConfig record);

    int updateByPrimaryKey(SkConfig record);

	int saveSksetting(SkConfig skconfig);

	/*SkConfig querySksetting(Integer kpdq);*/

	SkConfig initsetting(String nsrsbh);
}