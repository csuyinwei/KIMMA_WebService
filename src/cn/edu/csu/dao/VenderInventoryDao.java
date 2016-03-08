package cn.edu.csu.dao;

import cn.edu.csu.pojo.VenderInventory;


public interface VenderInventoryDao {
	
	void				save(VenderInventory venderInventory);
	
	void	 			deleteById(int id);
	
	void				update(VenderInventory venderInventory);
	
	void				add(int venderId, int salverNum,int channelNum, int invQty, int comdtyTransId);
		
	void 				decreaseById(int id);

	void				decreaseEarliestByVSC(int v,int s,int c);
	
	VenderInventory 	findEarlistByVSC(int venderId, int salverNum, int channelNum);

}
