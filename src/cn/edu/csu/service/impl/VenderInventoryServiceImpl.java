package cn.edu.csu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.csu.dao.VenderInventoryDao;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.pojo.VenderInventory;
import cn.edu.csu.service.VenderInventoryService;

@Service
public class VenderInventoryServiceImpl implements VenderInventoryService{
	
	@Autowired
	private VenderInventoryDao venderInventoryDao;

	@Override
	public void supply(VenderInventory venderInventory) {
		int venderId = venderInventory.getVender().getId();
		int salverNum = venderInventory.getSalverNumber();
		int channelNum = venderInventory.getChannelNumber();
		int invQty = venderInventory.getInventoryQuantity();
		int comdtyTransId = venderInventory.getCommodityTransport().getId();
		venderInventoryDao.add(venderId, salverNum, channelNum, comdtyTransId, invQty);
	}

	@Override
	public void sell(Vender vender, int salverNum, int channelNum) {
		venderInventoryDao.decreaseEarliestByVSC(vender.getId(), salverNum, channelNum);
	}

	@Override
	public CommodityTransport getTheFrontestCommodityByVSC(Vender vender, int salverNum, int channelNum) {
		VenderInventory venderInventory =venderInventoryDao.findEarlistByVSC(vender.getId(), salverNum, channelNum);
		if(venderInventory==null){
			return null;
		}
		return venderInventory.getCommodityTransport();
	}
}
