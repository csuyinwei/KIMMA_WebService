package cn.edu.csu.dao;

import java.util.List;

import cn.edu.csu.pojo.CommodityType;

public interface CommodityTypeDao {

	void 					save(CommodityType comdtyType);

	void	 				deleteById(int id);
	
	void 					update(CommodityType comdtyType);
	
	void	 				deleteByBarcode(String barcode);

	void	 				updateByBarcode(String oldBarcode, CommodityType comdtyType);
	
	List<CommodityType> 	listAll();
	
	List<CommodityType>		searchPagination(String search, int pageNum, int pageSize);
	
	CommodityType 			findByBarcode(String barcode);
	
	long 					getSearchCount(String search);

	List<CommodityType> searchByComdtyName(String comdtyName);
	
}
