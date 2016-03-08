package cn.edu.csu.service;


import java.util.Date;

import cn.edu.csu.common.TemperatureProcessAndTime;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.TemperatureData;






public interface TemperatureDataService {
	
	/**
	 * 功能： 保存温度数据
	 * 参数： TemperatureData对象
	 * 必填： commodityTransport,temperature
	 * 备注： id和submit字段字段被忽视，由系统自动生成
	 */
	void inputTemperatureData(TemperatureData tempData);
	
	
	/**
	 * 功    能： 获得某一商品配送批次的全部"配送阶段"以及对应的"配送阶段温度信息"
	 * 参 数1： 商品配送批次CommodityTransport
	 * 参 数2： 芯片记录温度的时间差，一般填10
	 * 返回值： TreeMap
	 * 备    注： 键String是阶段名，例如1.长沙站   2.长沙-广州
	 * 		  值ArrayList中存的是对应配送阶段的一条条温度数据，从0到n时间由旧到新
	 */
	TemperatureProcessAndTime getTemperatureProcess(CommodityTransport comdtyTrans,int timeInterval);
	
	Date getLastSubmitTime(CommodityTransport cmodtyTrans);
}
