package cn.edu.csu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.TransportProcess;
import cn.edu.csu.service.CommodityTransportService;
import cn.edu.csu.service.TransportProcessService;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TransportProcessServiceTest {
	
	@Autowired
	private TransportProcessService transportProcessService;
	
	@Autowired
	private CommodityTransportService commodityTransportService;
	
	@Test
	public void testInputDepartureInfo(){
		CommodityTransport comdtyTrans = commodityTransportService.getCurrentCommodityTransportByIBNumber("132");
		transportProcessService.inputDepartureInfo(comdtyTrans, "出发");
	}
	
	
	@Test
	public void testGetNull(){
		CommodityTransport comdtyTrans = new CommodityTransport();
		comdtyTrans.setId(13);
		TransportProcess tp = transportProcessService.getNullArrivalTransportProcess(comdtyTrans);
		System.out.println(tp.getDeparturePlace());
	}
}
