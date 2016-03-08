package cn.edu.csu.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CommodityType implements Serializable {
	

	private int 	id;
	private String  barcode;
	private String  commodityName;
	private String  brandName;
	private String  shelfLife;
	private String  remark;
	private int		isDelete;
	
	private Set commodityTransports = new HashSet(0);
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Set getCommodityTransports() {
		return commodityTransports;
	}
	public void setCommodityTransports(Set commodityTransports) {
		this.commodityTransports = commodityTransports;
	}
	
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
