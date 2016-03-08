package cn.edu.csu.service;

import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.pojo.VenderInventory;

public interface VenderInventoryService {
	
	/** 
	 * 功能：                   补货，即登记一个售货机库存信息
	 * 参数：                   VenderInventory
	 * 参数对象必填：    vender, salverNumber, channelNumber
	 *            inventoryQuantity, commodityTransport
	 * 备注：                   id,supplementTime,字段会被忽视，由系统自动生成
	 */
	void supply(VenderInventory venderInventory);
	
	
	/** 
	 * 功能：                   某售货机(V)的某托盘(S)的某货道(C)的最早补货的那记录的库存减少1，
	 *            即该货道上排在最前面的商品被售出。
	 * 参数：                   售货机：vender  托盘号：salverNum  货道号：channelNum
	 * 参数对象必填：    vender对象只要填入id即可。
	 * 备注：                   若decrease之后该记录库存变为0，则自动删除这条记录
	 */
	void sell(Vender vender,int salverNum, int channelNum);
	
	
	/** 
	 * 功能：                  查某售货机(V)的某托盘(S)的某货道(C)上最排在最前面的那件商品
	 * 参数：                   售货机：vender  托盘号：salverNum  货道号：channelNum
	 * 参数对象必填：    vender对象只要填入id即可。
	 * 返回值：	  CommodityTransport
	 */
	CommodityTransport getTheFrontestCommodityByVSC(Vender vender,int salverNum, int channelNum);
	
}
