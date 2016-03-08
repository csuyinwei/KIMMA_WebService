package cn.edu.csu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.csu.dao.AdminDao;
import cn.edu.csu.pojo.Admin;
import cn.edu.csu.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void register(Admin admin) {
		adminDao.save(admin);
	}

	@Override
	public Admin login(String account, String password) {
		return adminDao.findByAccountAndPassword(account, password);
	}

	@Override
	public boolean checkIfAccountExists(String name) {
		return adminDao.checkIfAccountExists(name);
	}
}
