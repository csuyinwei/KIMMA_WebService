package cn.edu.csu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.csu.dao.TransportProcessDao;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.TransportProcess;
import cn.edu.csu.service.TransportProcessService;


@Service
public class TransportProcessServiceImpl implements TransportProcessService {
	@Autowired
	private TransportProcessDao transportProcessDao;
	
	public TransportProcessDao getCommodityTransportDao() {
		return transportProcessDao;
	}

	public void setTransportProcessDao(TransportProcessDao transportProcessDao) {
		this.transportProcessDao = transportProcessDao;
	}

	@Override
	public void inputDepartureInfo(CommodityTransport comdtyTrans,String departurePlace) {
		transportProcessDao.addDeparturePlace(departurePlace, comdtyTrans.getId());
	}

	@Override
	public void inputArrivalInfo(TransportProcess transProc, String arrivalPlace) {
		transportProcessDao.updateArrivalPlace(arrivalPlace, transProc.getId());
	}

	@Override
	public TransportProcess getNullArrivalTransportProcess(CommodityTransport comdtyTrans) {
		return transportProcessDao.getNullArrivalTransportProcess(comdtyTrans.getId());
	}
}