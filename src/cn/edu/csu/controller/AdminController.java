package cn.edu.csu.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cn.edu.csu.pojo.Admin;
import cn.edu.csu.service.AdminService;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"admin"})
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	
	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	
	public boolean logined(Model model){
		return model.containsAttribute("admin");
	}
	
	/**
	 * 返回注册页面
	 */
	@RequestMapping("/register")
	public String toRegister() {
		return "admin/register";
	}

	
	
	/**
	 * 注册响应函数
	 */
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public String doRegister(Admin admin, Model model) {	
		if(admin.getAccount().equals("")){//账号不能为空
			return "/admin/register";
		}
		else if(admin.getPassword().equals("")){//密码不能为空
			return "/admin/register";
		}
		else if(admin.getName().equals("")){//姓名不能为空
			return "/admin/register";
		}
		try{
			adminService.register(admin);
		}catch(ConstraintViolationException e){
			model.addAttribute("regist_fail_msg", "duplicate_account");
			return "/admin/register";
		}
		model.addAttribute("admin", admin);
		return "../index";
	}
	
	
	
	/**
	 * 检查账号是否存在，如果存在返回true，否则返回false
	 */
	@RequestMapping("/checkDuplicate")
	@ResponseBody
	public boolean checkIfAccountExists(String account) {
		return adminService.checkIfAccountExists(account);
	}
	
	
	
	/**
	 * 先检查是否已经登录，
	 * 如果已经登录，跳转到提示页面
	 * 否则跳转到登录页面
	 */
	@RequestMapping("/login")
	public String toLogin(Model model) {
		if(logined(model)){
			return "redirect:../index";
		}
		return "/admin/login";
	}
	
	
	
	/**
	 * 登录响应函数
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	public String doLogin(String captcha, String account, String password, 
			Model model, HttpServletRequest request) {
		String targetCaptcha = (String) request.getSession().getAttribute("captcha");
		System.out.println(captcha);
		System.out.println(targetCaptcha);
		if(captcha ==null || !captcha.toLowerCase().equals(targetCaptcha)){
			return "wrong_captcha";
		}
		if(!adminService.checkIfAccountExists(account)){
			return "no_such_account";
		}
		Admin admin = adminService.login(account, password);
		if (admin == null) {
			return "wrong_password";
		} else {
			model.addAttribute("admin", admin);
			return "success";
		}
	}
	
	
	
	/**
	 * 登出响应函数
	 */
	@RequestMapping(value = "/doLogout")//, method = RequestMethod.POST)
	public String doLogout(SessionStatus sessionStatus) {
		 sessionStatus.setComplete();
		 return "redirect:../index";
	}

}
