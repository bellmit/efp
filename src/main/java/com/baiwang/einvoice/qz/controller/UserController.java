package com.baiwang.einvoice.qz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.User;

@Controller
@RequestMapping("user")
public class UserController {
	
	private Log logger = LogFactory.getLog(UserController.class);
	
	@RequestMapping(value="login",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String login(User user, HttpServletRequest request,HttpSession session){
		System.out.println(user.getUserName() + "--" + user.getUserPass());
		if(user != null && user.getUserName().equals("admin") && user.getUserPass().equals("e10adc3949ba59abbe56e057f20f883e")){
             
            session.setAttribute("user", user);
            logger.info("*********" + user.getUserName() + "登录成功");
            return "8888";
        }
		logger.info("*********" + user.getUserName() + "登录失败");
		
        return "4001";
	}
	
	@RequestMapping("getCurrentUser")
	@ResponseBody
	public Map<String, String> init(HttpServletRequest request){
		String basePath = request.getContextPath();
		
		User user = (User) request.getSession().getAttribute("user");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("basePath", basePath);
		if(null == user){
			map.put("currentUser", "");
		}else{
			map.put("currentUser", user.getUserName());
		}
		
		return map;
	}
	

}
