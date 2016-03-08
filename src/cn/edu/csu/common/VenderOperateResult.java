package cn.edu.csu.common;

/**
 *  商品类型添加或者修改的处理结果的返回类型
 */
public class VenderOperateResult {
	

	
	public String vender_number ;  
	
	public String vender_type ;
	
	public String salver_quantity ;
	
	public String channel_quantity ;
	
	public String capacity_per_channel;
	
	public String status;
	
	public boolean fail(){
		return vender_number != null || 
				vender_type != null || 
				salver_quantity != null || 
				channel_quantity != null || 
				capacity_per_channel!= null || 
				status != null;
	}
}
