package cn.edu.csu.dao;

import cn.edu.csu.pojo.Admin;

public interface AdminDao {

	void 	save(Admin admin);

	Admin 		findByAccountAndPassword(String account, String password);

	boolean 	checkIfAccountExists(String name);
	
}
