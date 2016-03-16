/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service;

import java.util.List;

import com.baiwang.einvoice.qz.beans.PortalFunction;
import com.baiwang.einvoice.qz.beans.User;

/**
  * @ClassName: IMenuService
  * @Description: 用户菜单Service
  * @author zhaowei
  * @date 2016年3月16日 上午9:46:32
  */
public interface IMenuService {

	public List<PortalFunction> getMenuByUser(User user);
}
