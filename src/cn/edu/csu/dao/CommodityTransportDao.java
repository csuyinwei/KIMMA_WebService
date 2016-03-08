package cn.edu.csu.dao;

import cn.edu.csu.pojo.CommodityTransport;

public interface CommodityTransportDao {

	void	 				save(CommodityTransport commodityTransport);

	void 				deleteById(int id);

	void 				update(CommodityTransport commodityTransport);

	void 				add(int comdtyTypeId, String ibNum, String prodDate, String prodOrigin);

	CommodityTransport 		findLatestByIbNum(String ibNum);
	
}
