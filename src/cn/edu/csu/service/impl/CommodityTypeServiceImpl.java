package cn.edu.csu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.csu.dao.CommodityTypeDao;
import cn.edu.csu.pojo.CommodityType;
import cn.edu.csu.service.CommodityTypeService;

@Service
public class CommodityTypeServiceImpl implements CommodityTypeService {
	@Autowired
	private CommodityTypeDao commodityTypeDao;
	
	public CommodityTypeDao getDao() {
		return commodityTypeDao;
	}

	public void setDao(CommodityTypeDao commodityTypeDao) {
		this.commodityTypeDao = commodityTypeDao;
	}


	@Override
	public void inputCommodityType(CommodityType comdtyType) {
		commodityTypeDao.save(comdtyType);
	}

	@Override
	public void updateCommodityTypeByBarcode(String oldBarcode, CommodityType comdtyType) {
		commodityTypeDao.updateByBarcode(oldBarcode, comdtyType);
	}

	@Override
	public CommodityType getCommodityTypeByBarcode(String barcode) {
		return commodityTypeDao.findByBarcode(barcode);
	}

	@Override
	public List<CommodityType> listAll() {
		return commodityTypeDao.listAll();
	}

	@Override
	public void deleteCommodityTypeByBarcode(String barcode) {
		commodityTypeDao.deleteByBarcode(barcode);
	}

	@Override
	public void updateCommodityType(CommodityType comdtyType) {
		commodityTypeDao.update(comdtyType);
	}

	@Override
	public List<CommodityType> searchPagination(String search, int pageNum, int pageSize) {
		return commodityTypeDao.searchPagination(search, pageNum, pageSize);
	}

	@Override
	public long getSearchCount(String search) {
		return commodityTypeDao.getSearchCount(search);
	}
	
	@Override
	public List<CommodityType> searchByCommodityName(String comdtyName){
		return commodityTypeDao.searchByComdtyName(comdtyName);
	}
}
