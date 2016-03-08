package cn.edu.csu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.csu.dao.CommodityTransportDao;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.service.CommodityTransportService;

@Service
public class CommodityTransportServiceImpl implements CommodityTransportService {
	@Autowired
	private CommodityTransportDao commodityTransportDao;
	
	public CommodityTransportDao getCommodityTransportDao() {
		return commodityTransportDao;
	}

	public void setCommodityTransportDao(CommodityTransportDao commodityTransportDao) {
		this.commodityTransportDao = commodityTransportDao;
	}

	@Override
	public void inputCommodityTransport(CommodityTransport comdtyTrans) {
		commodityTransportDao.add(
				comdtyTrans.getCommodityType().getId(), 
				comdtyTrans.getIntelligentBoxNumber(), 
				comdtyTrans.getProductDate(), 
				comdtyTrans.getProductOrigin());
	}

	@Override
	public CommodityTransport getCurrentCommodityTransportByIBNumber(String IBNumber) {
		return commodityTransportDao.findLatestByIbNum(IBNumber);
	}
}
