package com.baiwang.einvoice.qz.dao;

import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.ReportDetail;

public interface ReportDetailMapper {

    ReportDetail getFpByLSH(String fpqqlsh);
    
    List<ReportDetail> getFpListByCondition(Map<String, Object> condition);
}