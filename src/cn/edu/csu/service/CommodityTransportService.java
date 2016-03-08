package cn.edu.csu.service;

import cn.edu.csu.pojo.CommodityTransport;

public interface CommodityTransportService {

	/**
	 * 功能：                   登记配送信息
	 * 参数：                   商品配送批次CommodityTransport
	 * 参数对象必填：    commodityType,intelligentBoxNumber,productDate,productOrigin
	 * 备注：                    id,transportNumber 2个字段会被忽视，由系统自动生成
	 */
	void inputCommodityTransport(CommodityTransport comdtyTrans);
	
	
	/** 
	 * 功能：                   根据智能箱编号返回当前的配送批次信息
	 * 参数：                   智能箱编号
	 * 返回值：	  CommodityTransport                
	 */
	CommodityTransport getCurrentCommodityTransportByIBNumber(String IBNumber);
}
