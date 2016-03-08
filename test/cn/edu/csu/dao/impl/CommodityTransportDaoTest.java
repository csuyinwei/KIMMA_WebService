package cn.edu.csu.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.csu.dao.CommodityTransportDao;
import cn.edu.csu.dao.CommodityTypeDao;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class CommodityTransportDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CommodityTransportDao commodityTransportDao;
	
	@Autowired
	private CommodityTypeDao commodityTypeDao;

	@Test
	public void testSave() {
//		CommodityTransport commodityTransport = new CommodityTransport();
//		CommodityType commodityType = commodityTypeDao.findCommodityTypeByBarcode("1132515");	
//		commodityTransport.setCommodityType(commodityType);
//		commodityTransport.setIntelligentBoxNumber("133");
//		commodityTransport.setProductDate("2013-03");
//		commodityTransport.setProductOrigin("北京");
//		commodityTransportDao.save(commodityTransport);
	}
	
	@Test
	public void testFindEarliesByIbNum() {
//		CommodityTransport commodityTransport = commodityTransportDao.findEarliestByIbNum("132");
//		System.out.println(commodityTransport.getProductOrigin());
	}
	
	@Test
	public void testAdd() {
//		commodityTransportDao.add(1,"136","fas","s");
	}
	
	
}
	