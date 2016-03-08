package cn.edu.csu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.csu.dao.CommodityTypeDao;
import cn.edu.csu.pojo.CommodityType;

@Repository
public class CommodityTypeDaoImpl implements CommodityTypeDao {
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
	public List<CommodityType> listAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from CommodityType where isDelete = 0";
		return (List<CommodityType>) session.createQuery(hql).list();
	}

	@Override
	public void save(CommodityType comdtyType) {
		Session session = sessionFactory.getCurrentSession();
		session.save(comdtyType);
	}

	@Override
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE comdty_type SET is_delete = 1 ,barcode = CONCAT('d_',barcode) WHERE id = ? AND is_delete = 0";  
	    session.createSQLQuery(Sql)
	    	.setInteger(0, id)
	    	.executeUpdate();
	}

	@Override
	public void updateByBarcode(String oldBarcode, CommodityType comdtyType) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE comdty_type "
				+ "SET barcode = ?, comdty_name = ?, brand_name = ?, shelf_life = ?, remark = ? "
				+ "WHERE barcode = ? AND is_delete = 0";  
	    session.createSQLQuery(Sql)
	    	.setString(0, comdtyType.getBarcode())
	    	.setString(1, comdtyType.getCommodityName())
	    	.setString(2, comdtyType.getBrandName())
	    	.setString(3, comdtyType.getShelfLife())
	    	.setString(4, comdtyType.getRemark())
	    	.setString(5, oldBarcode)
	    	.executeUpdate();
	}

	@Override
	//SELECT * FROM comdty_type WHERE barcode = ?
	public CommodityType findByBarcode(String barcode) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM CommodityType ct "
				+ "WHERE ct.barcode = ? And isDelete = 0";
		Query query = session.createQuery(hql)
				.setString(0, barcode);
		return (CommodityType)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CommodityType> searchByComdtyName(String comdtyName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM CommodityType ct "
				+ "WHERE ct.isDelete = 0 and ct.commodityName like '%"+ comdtyName +"%'";
		Query query = session.createQuery(hql);
		return (List<CommodityType>)query.list();
	}
	

	@Override
	public void deleteByBarcode(String barcode) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE comdty_type SET is_delete = 1 , barcode = CONCAT('d_',barcode)  WHERE barcode = ? ";  
	    session.createSQLQuery(Sql)
	    	.setString(0, barcode)
	    	.executeUpdate();
	}

	@Override
	public void update(CommodityType comdtyType) {
		Session session = sessionFactory.getCurrentSession();
		String Sql = "UPDATE comdty_type "
				+ "SET barcode = ?, comdty_name = ?, brand_name = ?, shelf_life = ?, remark = ? "
				+ "WHERE id = ? And is_delete = 0";  
	    session.createSQLQuery(Sql)
	    	.setString(0, comdtyType.getBarcode())
	    	.setString(1, comdtyType.getCommodityName())
	    	.setString(2, comdtyType.getBrandName())
	    	.setString(3, comdtyType.getShelfLife())
	    	.setString(4, comdtyType.getRemark())
	    	.setInteger(5, comdtyType.getId())
	    	.executeUpdate();
	}

	public long getSearchCount(String search){
		Session session = sessionFactory.getCurrentSession();
		String hql;
		if("".equals(search)){
			hql = "Select Count(*) From CommodityType Where isDelete = 0";
		}
		else{
			hql = "Select Count(*) From CommodityType ct "
					+ "Where ct.isDelete = 0 And ct.commodityName like '%"+search+"%'";
		}
		Query query = session.createQuery(hql);
		return (long)query.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CommodityType> searchPagination(String search, int pageNum, int pageSize) {
		int offset = (pageNum-1)*pageSize;
		int limit = pageSize;
		Session session = sessionFactory.getCurrentSession();
		String hql;
		if("".equals(search)){
			hql = "From CommodityType Where isDelete = 0";
		}
		else{
			hql = "From CommodityType ct "
					+ "Where ct.isDelete = 0 And ct.commodityName like '%"+search+"%'";
		}
		Query query = session.createQuery(hql)
				.setFirstResult(offset)
				.setMaxResults(limit);
		return (List<CommodityType>)query.list();
	}

}
