package cn.edu.csu.service;

import java.util.List;

import cn.edu.csu.pojo.Vender;

public interface VenderService {


	/**
	 * 功能：                    登记自动售货机的信息
	 * 参数：                    售货机对象Vender
	 * 参数对象必填：     venderNumber, venderType, salverQuantity,
	 *             channelQuantity, capacityPerChannel, status
	 * 备注：                     id字段会被忽视，由系统自动生成            
	 */
	void inputVender(Vender vender);
	
	
	/** 
	 * 功能：     更新某一自动售货机的信息
	 * 参数：     售货机对象Vender
	 * 备注：     根据Vender中的id字段确定要更新的对象，
	 *       建议先根据id查出一个Vender,然后修改要更新的字段，然后再调用此函数
	 */
	void updateVender(Vender vender);
	
	
	/** 
	 * 功能：     根据售货机编号查询售货机
	 * 参数：     售货机编号String
	 */
	Vender getVenderByNumber(String number);
	
	
	List<Vender> listAll();
	
	
	List<String> listAllVenderNumber();

	
	void deleteByVenderNumber(String venderNumber);
	
	
	long getSearchCount(String search);

	
	List<Vender> searchPagination(String search, int pageNum, int pageSize);
}
