package cn.edu.csu.dao;

import java.util.List;
import cn.edu.csu.pojo.Vender;

public interface VenderDao {

	void	 		save(Vender vender);

	void	 		deleteById(int id);

	void			update(Vender vender);
	
	List<Vender> 	listAll();
	
	List<String> 	listAllVenderNumber();
	
	Vender 			findVenderByNumber(String venderNumber);
	
	void	 		deleteVenderByNumber(String venderNumber);
	
	long			getSearchCount(String search);

	List<Vender>	searchPagination(String search, int pageNum, int pageSize);

}
