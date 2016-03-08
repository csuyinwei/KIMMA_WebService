package cn.edu.csu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.service.CommodityTransportService;
import cn.edu.csu.service.CommodityTypeService;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class CommodityTransportServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CommodityTransportService commodityTransportService;
	
	@Autowired
	private CommodityTypeService commodityTypeService;
	
//	@Test
//	public void testInputCommodityTransport() {
//		CommodityTransport comdtyTrans = new CommodityTransport();
//		CommodityType commodityType = commodityTypeService.getCommodityTypeByBarcode("3132511");
//		comdtyTrans.setCommodityType(commodityType);
//		comdtyTrans.setIntelligentBoxNumber("172");
//		comdtyTrans.setProductDate("15年8月12号");
//		comdtyTrans.setProductOrigin("丽水");
//		comdtyTrans.setId(3);//该字段被无视！！
//		commodityTransportService.inputCommodityTransport(comdtyTrans);
//	}
	
	@Test
	public void testGetCur(){
		CommodityTransport comdtyTrans = commodityTransportService.getCurrentCommodityTransportByIBNumber("123");
		System.out.println(comdtyTrans);
	}
}