package cn.edu.csu.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.csu.dao.VenderDao;




@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class VenderDaoTest {
	
	@Autowired
	private VenderDao venderDao;
	
//	@Test
//	public void testSave(){
//		Vender vender = new Vender();
//		vender.setVenderNumber("V816812733");
//		vender.setVenderType("T132");
//		vender.setSalverQuantity(5);
//		vender.setChannelQuantity(4);
//		vender.setCapacityPerChannel(10);
//		vender.setStatus(1);
//		venderDao.save(vender);
//	}
	
//	@Test
//	public void testDeleteById(){
//		venderDao.deleteById(3);
//	}
	
	@Test
	public void testListNumber(){
		List<String> list = venderDao.listAllVenderNumber();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
