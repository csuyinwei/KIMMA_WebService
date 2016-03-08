package cn.edu.csu.service.impl;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.service.VenderService;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class VenderServiceTest {
	
	@Autowired
	private VenderService venderService;
	
//	@Test
//	public void testInput() {
//		Vender vender = new Vender();
//		vender.setVenderNumber("V1");
//		vender.setVenderType("T2");
//		vender.setSalverQuantity(10);
//		vender.setChannelQuantity(10);
//		vender.setCapacityPerChannel(5);
//		vender.setStatus(1);
//		venderService.inputVender(vender);
//	}
//	
//	@Test
//	public void testUpdate() {
//		Vender vender = new Vender();
//		vender.setId(10);
//		vender.setVenderNumber("SS");
//		vender.setVenderType("SS");
//		vender.setSalverQuantity(10);
//		vender.setChannelQuantity(10);
//		vender.setCapacityPerChannel(5);
//		vender.setStatus(1);
//		venderService.updateVender(vender);
//	}
//	
//	@Test
//	public void testGetVenderByNumber() {
//		Vender vender = venderService.getVenderByNumber("SS");
//		System.out.println(vender.getId());
//	}
//	
	@Test
	public void testPagination(){
		String search = "";
		System.out.println(venderService.getSearchCount(search));
		List<Vender> list = venderService.searchPagination(search, 1, 13);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getVenderNumber());
		}
	}
}
