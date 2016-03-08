package cn.edu.csu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.csu.dao.TemperatureDataDao;
import cn.edu.csu.pojo.TemperatureData;

@Repository
public class TemperatureDataDaoImpl implements TemperatureDataDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TemperatureData> findByCommodityTransportId(int comdtyTransId) {
				
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM TemperatureData td "
				+ "WHERE td.commodityTransport.id = ? "
				+ "ORDER BY td.submitTime";
		Query query = session.createQuery(hql)
		.setInteger(0, comdtyTransId);
		return (List<TemperatureData>)query.list();
	}

//	@Override
//	public void add(int comdityTransId, String temperature) {
//		Session session = sessionFactory.getCurrentSession();
//		String Sql = "INSERT INTO temp_data"
//				+ " (comdty_trans_id, temperature, submit_time) "
//				+ "VALUES(?, ?, now())";  
//	    session.createSQLQuery(Sql)
//	    .setInteger(0, comdityTransId)
//	    .setString(1, temperature)
//	    .executeUpdate();
//	}
	@Override
	public void add(int comdityTransId, String temperature) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "INSERT INTO temp_data"
				+ " (comdty_trans_id, temperature) "
				+ "VALUES(?, ?)";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, comdityTransId)
	    .setString(1, temperature)
	    .executeUpdate();
	}

	@Override
	public void save(TemperatureData temperatureData) {
		Session session = sessionFactory.getCurrentSession();
		session.save(temperatureData);
	}

	@Override
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "DELETE FROM temp_data WHERE id = ?";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, id)
	    .executeUpdate();
	}

	@Override
	public void update(TemperatureData temperatureData) {
		Session session = sessionFactory.getCurrentSession();
		session.update(temperatureData);
	}
}
