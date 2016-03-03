package com.baiwang.einvoice.qz.dao;

import com.baiwang.einvoice.qz.beans.ReportDetail;

public interface ReportDetailMapper {
    int deleteByPrimaryKey(String ddh);

    int insert(ReportDetail record);

    int insertSelective(ReportDetail record);

    ReportDetail selectByPrimaryKey(String ddh);

    int updateByPrimaryKeySelective(ReportDetail record);

    int updateByPrimaryKey(ReportDetail record);
}