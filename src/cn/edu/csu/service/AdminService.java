package cn.edu.csu.service;

import org.springframework.stereotype.Service;

import cn.edu.csu.pojo.Admin;

@Service
public interface AdminService {
	
	void 	register(Admin admin);

	Admin 		login(String account, String password);

	boolean 	checkIfAccountExists(String name);

}
