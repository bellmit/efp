package com.baiwang.einvoice.qz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.service.IUserService;

@Controller
@RequestMapping("")
public class UserController {
	
	private Log logger = LogFactory.getLog(UserController.class);
	
	@Resource
	private IUserService service;
	
	@RequestMapping(value="user/login",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String login(User user, HttpServletRequest request,HttpSession session){
		
		User _user = service.getUserByName(user.getUserName());
		if(_user != null && user.getUserPass().equals(_user.getUserPass())){
             
            session.setAttribute("user", _user);
            logger.info("*********" + user.getUserName() + "登录成功");
            return "8888";
        }
		logger.info("*********" + user.getUserName() + "登录失败");
		
        return "4001";
	}
	
	@RequestMapping("user/getCurrentUser")
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
	
	@RequestMapping("/logout")
	@ResponseBody
	public String exit(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		
		return "0";
	}

	@RequestMapping("user/changePwd")
	@ResponseBody
	public HashMap<String, String> changePwd(HttpServletRequest request, HttpSession session){
		HashMap<String, String> result = new HashMap<>();
		User user = (User) session.getAttribute("user");
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		String newpassword = request.getParameter("newpassagain");
		
		if(StringUtils.isEmpty(newpass)||StringUtils.isEmpty(newpassword)||StringUtils.isEmpty(oldpass)){
			result.put("status", "error");
			result.put("msg", "密码不能为空！");
			return result;
		}
		if(!newpass.equals(newpassword)){
			result.put("status", "error");
			result.put("msg", "两次输入的新密码不一致！");
			return result;
		}
		if(newpass.equals(oldpass)){
			result.put("status", "error");
			result.put("msg", "新密码不能与旧密码相同！");
			return result;
		}
		int count = service.selectUserByPass(user.getId(), oldpass); 
		if(count==0){
			result.put("status", "error");
			result.put("msg", "原密码输入错误！");
			return result;
		}
		
		service.changePass(user.getId(), newpass);
		
		result.put("status", "success");
		result.put("msg", "密码修改成功！");
		return result;
	}
}
