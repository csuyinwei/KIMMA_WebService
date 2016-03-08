package cn.edu.csu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.pojo.VenderInventory;
import cn.edu.csu.service.CommodityTransportService;
import cn.edu.csu.service.VenderInventoryService;
import cn.edu.csu.service.VenderService;

@ContextConfiguration(locations = {"file:WebContent/WEB-INF/springmvc-servlet.xml", "classpath:spring-config/spring_*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class VenderInventoryServiceTest {
	
	@Autowired
	private VenderInventoryService venderInventoryService;
	
	@Autowired
	private VenderService venderService;
	
	@Autowired
	private CommodityTransportService commodityTransportService;
	
	@Test
	public void testInput(){
		VenderInventory venderInventory = new VenderInventory();
		Vender vender = venderService.getVenderByNumber("V816812732");
		CommodityTransport commodityTransport = commodityTransportService.getCurrentCommodityTransportByIBNumber("132");
		venderInventory.setVender(vender);
		venderInventory.setCommodityTransport(commodityTransport);
		venderInventory.setSalverNumber(1);
		venderInventory.setChannelNumber(1);
		venderInventory.setInventoryQuantity(5);
		venderInventoryService.supply(venderInventory);
	}
	
	@Test
	public void testDecrease(){
		Vender vender = venderService.getVenderByNumber("V816812732");
		venderInventoryService.sell(vender,1,1);
	}
}
