package cn.edu.csu.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.csu.dao.CommodityTypeDao;
import cn.edu.csu.pojo.CommodityType;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class CommodityTypeDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private CommodityTypeDao commodityTypeDao;

//	@Test
//	public void testListAll() {
//		List<CommodityType> commodityTypes = commodityTypeDao.listAll();
//		for(int i=0;i<commodityTypes.size();i++){
//			System.out.println(commodityTypes.get(i).getCommodityName());
//		}
//		assertTrue(commodityTypes.get(0).getBrandName().equals("光明"));
//	}
//	
//	@Test
//	public void testSave() {
//		CommodityType comdtyType = new CommodityType();
//		comdtyType.setBarcode("001");
//		comdtyType.setCommodityName("矿泉水");
//		comdtyType.setBrandName("农夫山泉");
//		comdtyType.setShelfLife("2年");
//		commodityTypeDao.save(comdtyType);
//	}
//	
//	@Test
//	public void testDeleteById() {
//		commodityTypeDao.deleteById(10);
//	}
//	
//	@Test
//	public void testUpdate() {
//		
//		CommodityType comdtyType = new CommodityType();
//		comdtyType.setId(16);
//		comdtyType.setBarcode("003");
//		comdtyType.setCommodityName("矿泉水");
//		comdtyType.setBrandName("农夫山泉");
//		comdtyType.setShelfLife("2年");
//		commodityTypeDao.update(comdtyType);
//		
//	}
//	
//	
//	@Test
//	public void testfindCommodityTypeByBarcode() {
//		CommodityType commodityType = commodityTypeDao.findByBarcode("152009");
//	}
	
	@Test
	public void testPagination() {
		String search = "奶";
		System.out.println(commodityTypeDao.getSearchCount(search));
		List<CommodityType> list = commodityTypeDao.searchPagination(search,2, 3);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getCommodityName());
		}
	}
	
//	@Test
//	public void testGetCount(){
//		System.out.println(commodityTypeDao.getCount());
//	}
}
