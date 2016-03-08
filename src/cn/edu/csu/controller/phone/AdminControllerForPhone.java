package cn.edu.csu.controller.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.edu.csu.pojo.Admin;
import cn.edu.csu.service.AdminService;

@Controller
@RequestMapping("/phone/admin")
@SessionAttributes({"phone_admin"})
public class AdminControllerForPhone {
	
	@Autowired
	private AdminService adminService;

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping("/checkDuplicate")
	@ResponseBody
	public boolean checkIfAccountExists(String name) {
		return adminService.checkIfAccountExists(name);
	}
	
//	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
//	@ResponseBody
//	//http://localhost:8080/cclms/phone/admin/doLogin?account=zgx&password=1
//	public String doLogin(String account, String password, Model model) {
//		if(!adminService.checkIfAccountExists(account)){
//				return "no_such_account";
//		}
//		Admin admin = adminService.login(account, password);
//		if (admin == null) {
//			return "wrong_password";
//		} else {
//			model.addAttribute("admin", admin);
//			return "success";
//		}
//	}
	
	@RequestMapping(value = "/doLogin") //, method = RequestMethod.POST)
	@ResponseBody
	//http://localhost:8080/cclms/phone/admin/doLogin?account=zgx&password=1
	public boolean doLogin(String account, String password, Model model) {
		
		System.out.println(account+" "+password);
		if(!adminService.checkIfAccountExists(account)){
				return false;
		}
		Admin admin = adminService.login(account, password);
		if (admin == null) {
			return false;
		} else {
			//model.addAttribute("phone_admin", admin);//!!!!!!!!!
			return true;
		}
	}
}
