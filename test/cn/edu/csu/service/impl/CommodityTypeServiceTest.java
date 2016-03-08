package cn.edu.csu.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.csu.pojo.CommodityType;
import cn.edu.csu.service.CommodityTypeService;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class CommodityTypeServiceTest {
	
	@Autowired
	private CommodityTypeService commodityTypeService;
	
//	@Test
//	public void test(){
//		CommodityType commodityType = commodityTypeService.getCommodityTypeByBarcode("3132511");
//		System.out.println(commodityType.getCommodityName());
//	}
	
//	@Test
//	public void testUpdate(){
//		CommodityType comdtyType = new CommodityType();
//		comdtyType.setId(19);
//		comdtyType.setBarcode("1");
//		comdtyType.setCommodityName("2");
//		comdtyType.setBrandName("农3");
//		comdtyType.setShelfLife("4");
//		commodityTypeService.updateCommodityType(comdtyType);
//	}
//	
	@Test
	public void testSearch(){
		List<CommodityType> list = commodityTypeService.searchByCommodityName("鲜牛奶");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getCommodityName());
		}
	}
}
