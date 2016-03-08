package cn.edu.csu.common;

/**
 *  商品类型添加或者修改的处理结果的返回类型
 */
public class CommodityTypeOperateResult {
	

	
	public String commodity_name ;  
	
	public String barcode ;
	
	public String brand_name ;
	
	public String shelf_life ;
	
	public boolean fail(){
		return commodity_name != null || 
				barcode != null || 
				brand_name != null || 
				shelf_life != null;
	}
}
