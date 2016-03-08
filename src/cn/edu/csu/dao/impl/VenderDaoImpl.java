package cn.edu.csu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.csu.dao.VenderDao;
import cn.edu.csu.pojo.Vender;

@Repository
public class VenderDaoImpl implements VenderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vender> listAll() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Vender>) session.createQuery("from Vender Where isDelete = 0").list();
	}

	@Override
	public void save(Vender vender) {
		Session session = sessionFactory.getCurrentSession();
		session.save(vender);
	}

	@Override
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE vender SET is_delete = 1, vender_num = CONCAT('d_',vender_num) WHERE id = ?";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, id)
	    .executeUpdate();
	}

	@Override
	public void update(Vender vender) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE vender "
				+ "SET vender_num = ?, vender_type = ?, "
				+ "salver_qty = ?, channel_qty = ?, "
				+ "cap_per_chan = ?, status = ? "
				+ "WHERE id = ? And is_delete = 0";  
	    session.createSQLQuery(Sql)
	    .setString(0, vender.getVenderNumber())
	    .setString(1, vender.getVenderType())
	    .setInteger(2, vender.getSalverQuantity())
	    .setInteger(3, vender.getChannelQuantity())
	    .setInteger(4, vender.getCapacityPerChannel())
	    .setInteger(5, vender.getStatus())
	    .setInteger(6, vender.getId())
	    .executeUpdate();
	}

	@Override
	public Vender findVenderByNumber(String number) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Vender v WHERE v.venderNumber = ? And isDelete = 0";
		Query query = session.createQuery(hql)
		.setString(0, number);
		return (Vender)query.uniqueResult();
	}

	@Override
	public void deleteVenderByNumber(String venderNumber) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE vender SET is_delete = 1, vender_num = CONCAT('d_',vender_num) WHERE vender_num = ?";  
	    session.createSQLQuery(Sql)
	    .setString(0, venderNumber)
	    .executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listAllVenderNumber() {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "SELECT vender_num FROM vender Where is_delete = 0";  
		return (List<String>)session.createSQLQuery(Sql).list();
	}

	@Override
	public long getSearchCount(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql;
		if("".equals(search)){
			hql = "Select Count(*) From Vender Where isDelete = 0";
		}
		else{
			hql = "Select Count(*) From Vender Where isDelete = 0 And venderNumber like '%"+search+"%'";
		}
		Query query = session.createQuery(hql);
		return (long)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vender> searchPagination(String search, int pageNum, int pageSize) {
		int offset = (pageNum-1)*pageSize;
		int limit = pageSize;
		Session session = sessionFactory.getCurrentSession();
		String hql;
		if("".equals(search)){
			hql = "From Vender Where isDelete = 0";
		}
		else{
			hql = "From Vender Where isDelete = 0 And venderNumber like '%"+search+"%'";
		}
		Query query = session.createQuery(hql)
				.setFirstResult(offset)
				.setMaxResults(limit);
		return (List<Vender>)query.list();
	}
}




