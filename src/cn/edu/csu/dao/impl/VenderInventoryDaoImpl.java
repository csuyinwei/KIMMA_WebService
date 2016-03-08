package cn.edu.csu.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.edu.csu.dao.VenderInventoryDao;
import cn.edu.csu.pojo.VenderInventory;

@Repository
public class VenderInventoryDaoImpl implements VenderInventoryDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void decreaseById(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("UPDATE vender_inv SET inv_qty = inv_qty - 1 WHERE id = ?")
		.setInteger(0, id)
		.executeUpdate();
	}

	@Override
	public VenderInventory findEarlistByVSC(int venderId,int salverNum, int channelNum) {

		Session session = sessionFactory.getCurrentSession();
		String hql = "from VenderInventory vi "
				+ "where vi.vender.id= ? and salverNumber = ? and channelNumber = ? "
				+ "order by vi.supplementTime ";
		
		Query query = session.createQuery(hql)
		.setInteger(0, venderId)
		.setInteger(1, salverNum)
		.setInteger(2, channelNum)
		.setMaxResults(1);

		return (VenderInventory)query.uniqueResult();
		
	}

	@Override
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "DELETE FROM vender_inv WHERE id = ?";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, id)
	    .executeUpdate();
	}

	@Override
	public void add(int venderId, int salverNum, int channelNum, int comdtyTransId, int invQty) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "INSERT INTO vender_inv "
				+ "(vender_id, salver_num, channel_num, comdty_trans_id, inv_qty)"
				+ " VALUES(?,?,?,?,?)";  
	    session.createSQLQuery(Sql)
	    .setInteger(0, venderId)
	    .setInteger(1, salverNum)
	    .setInteger(2, channelNum)
	    .setInteger(3, comdtyTransId)
	    .setInteger(4, invQty)
	    .executeUpdate();
	}

	@Override
	public void decreaseEarliestByVSC(int v, int s, int c) {
		Session session = sessionFactory.getCurrentSession();
		String procSql = "{Call decrease_inventory(?,?,?)}";  
	    session.createSQLQuery(procSql)
	    .setInteger(0, v)
	    .setInteger(1, s)
	    .setInteger(2, c)
	    .executeUpdate();
	}

	@Override
	public void save(VenderInventory venderInventory) {
		Session session = sessionFactory.getCurrentSession();
		session.save(venderInventory);
	}

	@Override
	public void update(VenderInventory venderInventory) {
		Session session = sessionFactory.getCurrentSession();
		session.update(venderInventory);
	}

}
