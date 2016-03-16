/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.PortalFunction;
import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.dao.PortalFunctionMapper;
import com.baiwang.einvoice.qz.service.IMenuService;

/**
  * @ClassName: MenuServiceImpl
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月16日 上午10:12:49
  */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	@Resource
	private PortalFunctionMapper dao;
	/**
	  * <p>Title: getMenuByUser</p>
	  * <p>Description: </p>
	  * @param user
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IMenuService#getMenuByUser(com.baiwang.einvoice.qz.beans.User)
	  */
	@Override
	public List<PortalFunction> getMenuByUser(User user) {

		// TODO Auto-generated method stub
		return dao.getMenuByUser(user.getCjrdm());

	}

}
