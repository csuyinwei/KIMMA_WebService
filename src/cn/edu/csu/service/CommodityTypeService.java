package cn.edu.csu.service;

import java.util.List;

import cn.edu.csu.pojo.CommodityType;

public interface CommodityTypeService {

	List<CommodityType> listAll();
	
	/** 
	 *  功能：                   保存一个商品类型的信息
	 *  参数：                   商品类型对象CommodityType 
	 *  参数对象必填：    barcode,commodityName,brandName,shelfLife
	 *  参数对象选填：    remarks
	 *  备注：                   id,字段会被忽视，由系统自动生成
	 */
	void inputCommodityType(CommodityType comdtyType);
	
	
	/**
	 * 功能：     更新某一商品类型的信息
	 * 参数：     商品类型对象CommodityType
	 * 备注：     根据CommodityType中的id字段确定要更新的对象，
	 *       建议先根据id或者二维码查出一个CommodityType,然后修改要更新的字段，然后再调用此函数
	 */
	void updateCommodityType(CommodityType comdtyType);
	
	
	/**
	 * 功能：     根据二维码获得CommodityType
	 * 参数：     二维码字符串
	 * 返回值： CommodityType
	 */
	CommodityType getCommodityTypeByBarcode(String barcode);
	
	List<CommodityType> searchPagination(String search, int pageNum, int pageSize);
	
	void updateCommodityTypeByBarcode(String oldBarcode, CommodityType comdtyType);
	
	void deleteCommodityTypeByBarcode(String barcode);
	
	long getSearchCount(String search);

	List<CommodityType> searchByCommodityName(String comdtyName);

}
