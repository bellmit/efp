package com.baiwang.einvoice.qz.dao;

import java.util.List;

import com.baiwang.einvoice.qz.beans.PortalFunction;

public interface PortalFunctionMapper {
    int insert(PortalFunction record);

    int insertSelective(PortalFunction record);
    
    List<PortalFunction> list();
    
    List<PortalFunction> getMenuByUser(String role_name);
}