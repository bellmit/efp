/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.PortalFunction;
import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.service.IMenuService;

/**
  * @ClassName: MenuController
  * @Description: 菜单Controller
  * @author zhaowei
  * @date 2016年3月15日 下午5:51:06
  */
@Controller
@RequestMapping("")
public class MenuController {

	@Resource
	private IMenuService menuService;
	
	@RequestMapping("/menu")
	@ResponseBody
	public Object getMenu(HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user == null){
			return null;
		}
		List<PortalFunction> listMenu = menuService.getMenuByUser(user);
		
		return listMenu;
	}
}
