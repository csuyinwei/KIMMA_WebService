package cn.edu.csu.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.edu.csu.dao.CommodityTransportDao;
import cn.edu.csu.pojo.CommodityTransport;

@Repository
public class CommodityTransportDaoImpl implements CommodityTransportDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public CommodityTransport findLatestByIbNum(String ibNum) {
			System.out.println(ibNum);
		Session session = sessionFactory.getCurrentSession();
		String hql = "From CommodityTransport ct "
				+ "WHERE ct.intelligentBoxNumber = ? "
				+ "ORDER BY ct.transportNumber DESC";
		
		Query query = session.createQuery(hql)
		.setString(0, ibNum)
		.setMaxResults(1);
		return (CommodityTransport)query.uniqueResult();
	}
	
	@Override
	public void save(CommodityTransport commodityTransport) {
		Session session = sessionFactory.getCurrentSession();
		session.save(commodityTransport);
	}

	@Override
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "DELETE FROM comdty_trans WHERE id = ?";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, id)
	    .executeUpdate();
	}

	@Override
	public void update(CommodityTransport commodityTransport) {
		Session session = sessionFactory.getCurrentSession();
		session.update(commodityTransport);
	}

	@Override
	public void add(int comdtyTypeId, String ibNum, String prodDate,String prodOrigin) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "INSERT INTO comdty_trans (comdty_type_id, ib_num, prod_date, prod_origin) "
				+ "VALUES(?, ?, ?, ?)";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, comdtyTypeId)
	    .setString(1,ibNum)
	    .setString(2,prodDate)
	    .setString(3,prodOrigin)
	    .executeUpdate();
	}
}
