package com.baiwang.einvoice.qz.dao;

import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.beans.ReportTotal;

public interface ReportDetailMapper {

    ReportDetail getFpByLSH(String fpqqlsh);
    
    ReportTotal getFpStatByLSH(String fpqqlsh);
    
    List<ReportDetail> getFpListByCondition(Map<String, Object> condition);
    
    List<ReportTotal> getFpStatListByCondition(Map<String, Object> condition);
}