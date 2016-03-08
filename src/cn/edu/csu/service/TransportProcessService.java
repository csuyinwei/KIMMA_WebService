package cn.edu.csu.service;

import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.TransportProcess;

public interface TransportProcessService {
	
	/** 
	 * 功能：    登记某配送批次从某一站点离开
	 * 参数：    商品配送对象CommodityTransport，离开站的名称String
	 * 备注：    当前配送信息CommodityTransport对象可以通过ibNumber智能箱编号获得
	 */
	void inputDepartureInfo(CommodityTransport comdtyTrans, String departurePlace);
	
		
	/** 
	 * 功能：    登记某配送批次到达某一站点
	 * 参数：    要修改的对象TransportProcess，到达站的名称String
	 * 备注：    TransportProcess由getNullArrivalTransportProcess函数获得。
	 */
	void inputArrivalInfo(TransportProcess transProc, String arrivalPlace);
	
	TransportProcess getNullArrivalTransportProcess(CommodityTransport comdtyTrans);
}
