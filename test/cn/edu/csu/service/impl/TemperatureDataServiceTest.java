package cn.edu.csu.service.impl;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.csu.common.TemperatureProcess;
import cn.edu.csu.common.TemperatureProcessAndTime;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.service.CommodityTransportService;
import cn.edu.csu.service.TemperatureDataService;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TemperatureDataServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private TemperatureDataService temperatureDataService;
	
	@Autowired
	private CommodityTransportService commodityTransportService;
	
	@Test
	public void test() {
		CommodityTransport comdtyTrans = new CommodityTransport();
		comdtyTrans.setId(1);
		TemperatureProcessAndTime tpt = temperatureDataService.getTemperatureProcess(comdtyTrans, 10);
		ArrayList<TemperatureProcess> list = tpt.getTemperatureProcessList();
		for(int i=0;i<list.size();i++){
			System.out.print(list.get(i).getProcess());
			System.out.println(list.get(i).getTemperature());
		}
		System.out.println(tpt.getLastSubmitTimeOrFailInfo());
	}
//	
//	@Test
//	public void testInput(){
//		CommodityTransport ct = commodityTransportService.getCurrentCommodityTransportByIBNumber("132");
//		TemperatureData td = new TemperatureData();
//		td.setCommodityTransport(ct);
//		td.setTemperature("1,2,3,4,5");
//		temperatureDataService.inputTemperatureData(td);
//	}
	
//	@Test
//	public void testGetLastSubmitDate() {
//		CommodityTransport comdtyTrans = new CommodityTransport();
//		comdtyTrans.setId(1);
//		System.out.println(temperatureDataService.getLastSubmitTime(comdtyTrans));
//	}
	
	
}
