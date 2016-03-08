package cn.edu.csu.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.csu.dao.VenderInventoryDao;


@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class VenderInventoryDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private VenderInventoryDao venderInventoryDao;

	@Test
	public void testDecreaseByVsc() {
		//venderInventoryDao.decreaseEarliestInventoryQuantityByVSC(1,1,1);
	}
	
	@Test
	public void testDecrease() {
		//venderInventoryDao.decreaseInventoryQuantityById(8);
	}
	
	@Test
	public void testDeleteById() {
		//venderInventoryDao.deleteVenderInventoryById(15);
	}
	
	@Test
	public void testAddVenderInventory() {
		venderInventoryDao.add(1,3,2,2,13);
	}
	
	@Test
	public void testFindEarlistVenderInventory() {
		//System.out.println(venderInventoryDao.findEarlistVenderInventory(2,1,1).getId());
	}
}


