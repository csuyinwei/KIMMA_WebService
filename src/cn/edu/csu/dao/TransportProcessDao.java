package cn.edu.csu.dao;

import java.util.List;
import cn.edu.csu.pojo.TransportProcess;

public interface TransportProcessDao {
	
	void						save(TransportProcess transportProcess);
	
	void						deleteById(int id);
	
	void						update(TransportProcess transportProcess);

	List<TransportProcess> 		findByCommodityTransportId(int comdtyTransId);
	
	void 						addDeparturePlace(String deptPlace,int comdityTransId);
		
	void  	 					updateArrivalPlace(String arrPlace,int transProcId);
	
	TransportProcess 			getNullArrivalTransportProcess(int comdityTransId);
}
