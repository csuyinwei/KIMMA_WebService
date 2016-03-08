package cn.edu.csu.dao;

import java.util.List;
import cn.edu.csu.pojo.TemperatureData;

public interface TemperatureDataDao {
	
	void					save(TemperatureData temperatureData);
	
	void					deleteById(int id);
	
	void					update(TemperatureData temperatureData);
	
	void					add(int comdityTransId,String temperature);
	
	List<TemperatureData> 	findByCommodityTransportId(int comdtyTransId);
	
}
