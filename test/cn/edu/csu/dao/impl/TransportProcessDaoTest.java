package cn.edu.csu.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.csu.dao.TransportProcessDao;
import cn.edu.csu.pojo.TransportProcess;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TransportProcessDaoTest {

	@Autowired
	private TransportProcessDao transportProcessDao;
	
	@Test
	public void testFindTransportProcessByCommodityTransportId(){
		List<TransportProcess> list = transportProcessDao.findByCommodityTransportId(2);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getDeparturePlace());
		}
	}
	
	@Test
	public void testAddTransportProcess(){
		transportProcessDao.addDeparturePlace("FUCK",2);
	}
	
	@Test
	public void testUpdateTransportProcess(){
		transportProcessDao.updateArrivalPlace("SHIT",2);
	}
	
}
