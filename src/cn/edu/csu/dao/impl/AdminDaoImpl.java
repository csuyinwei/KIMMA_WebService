package cn.edu.csu.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.csu.dao.AdminDao;
import cn.edu.csu.pojo.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		session.save(admin);
	}

	@Override
	public Admin findByAccountAndPassword(String account, String password) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Admin admin where admin.account=:account and admin.password=:password";
		return (Admin) session.createQuery(hql)
								.setParameter("account", account)
								.setParameter("password", password)
								.uniqueResult();
	}

	@Override
	public boolean checkIfAccountExists(String account) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(admin.id) from Admin admin where admin.account = :account";
		long num = (long) session.createQuery(hql)
		.setString("account", account)
		.uniqueResult();
		if (num > 0) 
			return true;
		return false;
	}
	
	

}
