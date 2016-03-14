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
import org.springframework.web.servlet.ModelAndView;

import com.baiwang.einvoice.qz.beans.Page;
import com.baiwang.einvoice.qz.beans.User;
import com.baiwang.einvoice.qz.service.IUserService;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

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
			if(!"Y".equals(_user.getQybz())){
				logger.info("*********" + user.getUserName() + "登录失败");
		        return "4002";
			}else if(!"1".equals(_user.getYhlx())){
				logger.info("*********" + user.getUserName() + "登录失败");
		        return "4003";
			}else{
				_user.setUserName(user.getUserName());
	            session.setAttribute("user", _user);
	            logger.info("*********" + user.getUserName() + "登录成功");
	            return "8888";
			}
		}else{
			logger.info("*********" + user.getUserName() + "登录失败");
	        return "4001";
		}
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
	
	@RequestMapping("/user/list")
	@ResponseBody
	public ModelAndView list(Page page , HttpServletRequest request){
		
		HashMap<String, String> param = new HashMap<>();
		
		String currentPage = request.getParameter("currentPage");
		if (!(null == currentPage || "".equals(currentPage))) {
			page.setPageIndex(Integer.parseInt(currentPage));
		}
		//List<Map<String, String>> kpxxList = fpService.getPlainList(param);
		PageList<HashMap<String, Object>> userList = (PageList<HashMap<String, Object>>) service.listUser(param, page.getPageIndex(), page.getPageSize());
		page.setPageSize(userList.getPaginator().getLimit()); 
		page.setTotalCounts(userList.getPaginator().getTotalCount());
		page.setTotalPages(userList.getPaginator().getTotalPages());
		request.setAttribute("page", page);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/listUser");
		mav.addObject("param", param);
		mav.addObject("userList", userList);
		return mav;
	}
	@RequestMapping("/user/update")
	public String updateUser(HttpServletRequest request){
		String userId = request.getParameter("id");
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		int id = 0;
		try {
			id = Integer.parseInt(userId);
			User user=service.selectById(id);
			request.setAttribute("user", user);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		}
		return "/user/editUser";
	}
	
	@RequestMapping("/user/save")
	public String saveUser(User user){
		service.updateById(user);
		
		return "redirect:/user/list";
	}
}
