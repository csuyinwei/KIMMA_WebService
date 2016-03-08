package cn.edu.csu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.csu.dao.TransportProcessDao;
import cn.edu.csu.pojo.TransportProcess;

@Repository
public class TransportProcessDaoImpl implements TransportProcessDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(TransportProcess transportProcess) {
		Session session = sessionFactory.getCurrentSession();
		session.save(transportProcess);
	}

	@Override
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "DELETE FROM trans_proc WHERE id = ?";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, id)
	    .executeUpdate();
	}

	@Override
	public void update(TransportProcess transportProcess) {
		Session session = sessionFactory.getCurrentSession();
		session.update(transportProcess);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//SELECT * FROM trans_proc WHERE comdty_trans_id = ? ORDER BY dept_time
	public List<TransportProcess> findByCommodityTransportId(int comdtyTransId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM TransportProcess tp "
				+ "WHERE tp.commodityTransport.id = ? "
				+ "ORDER BY tp.departureTime";
		Query query = session.createQuery(hql)
		.setInteger(0, comdtyTransId);
		return (List<TransportProcess>)query.list();
	}

//	@Override
//	//INSERT INTO trans_proc (dept_place, dept_time, comdty_trans_id) VALUES(?, now(), ?)
//	public void addDeparturePlace(String deptPlace, int comdityTransId) {
//		Session session = sessionFactory.getCurrentSession();
//		String Sql = "INSERT INTO trans_proc (dept_place, dept_time, comdty_trans_id) "
//				+ "VALUES(?, now(), ?)";  
//	    session.createSQLQuery(Sql)
//	    .setString(0, deptPlace)
//	    .setInteger(1, comdityTransId)
//	    .executeUpdate();
//	}
	@Override
	public void addDeparturePlace(String deptPlace, int comdityTransId) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "INSERT INTO trans_proc (dept_place, comdty_trans_id) "
				+ "VALUES(?, ?)";  
	    session.createSQLQuery(Sql)
	    .setString(0, deptPlace)
	    .setInteger(1, comdityTransId)
	    .executeUpdate();
	}

//	@Override
//	//UPDATE trans_proc SET arr_place = ?, arr_time = now() WHERE comdty_trans_id = ? AND arr_place IS NULL
//	public void updateArrivalPlace(String arrPlace, int transProcId) {
//		Session session = sessionFactory.getCurrentSession();
//		String Sql = "UPDATE trans_proc "
//				+ "SET arr_place = ?, arr_time = now() "
//				+ "WHERE id = ?";  
//	    session.createSQLQuery(Sql)
//	    .setString(0, arrPlace)
//	    .setInteger(1, transProcId)
//	    .executeUpdate();
//	}
	@Override
	//UPDATE trans_proc SET arr_place = ?, arr_time = now() WHERE comdty_trans_id = ? AND arr_place IS NULL
	public void updateArrivalPlace(String arrPlace, int transProcId) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE trans_proc "
				+ "SET arr_place = ? "
				+ "WHERE id = ?";  
	    session.createSQLQuery(Sql)
	    .setString(0, arrPlace)
	    .setInteger(1, transProcId)
	    .executeUpdate();
	}

	@Override
	public TransportProcess getNullArrivalTransportProcess(int comdityTransId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM TransportProcess tp "
				+ "WHERE tp.commodityTransport.id = ? AND tp.arrivalPlace IS NULL";
		Query query = session.createQuery(hql)
		.setInteger(0, comdityTransId);
		return (TransportProcess)query.uniqueResult();
	}
	
	
}
