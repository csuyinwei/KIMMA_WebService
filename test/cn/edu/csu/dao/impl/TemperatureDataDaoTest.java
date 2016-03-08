package cn.edu.csu.dao.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.csu.dao.TemperatureDataDao;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TemperatureDataDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private TemperatureDataDao temperatureDataDao;
	
//	@Test
//	public void testFindTemperatureDataByCommodityTransportId() {
//		List<TemperatureData> list = temperatureDataDao.findByCommodityTransportId(1);
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).getTemperature());
//		}
//	}
	
//	@Test
//	public void testAddTemperatureData() {
//		temperatureDataDao.add(2, "1,2,3");
//	}
	
}
