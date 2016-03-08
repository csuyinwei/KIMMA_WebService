package cn.edu.csu.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CommodityTransport implements Serializable {
	
	private int 		id;
	private Date 		transportNumber;
	private String 	 	intelligentBoxNumber;
	private String	 	productDate;
	private String 	 	productOrigin;
	private CommodityType commodityType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTransportNumber() {
		return transportNumber;
	}
	public void setTransportNumber(Date transportNumber) {
		this.transportNumber = transportNumber;
	}
	public String getIntelligentBoxNumber() {
		return intelligentBoxNumber;
	}
	public void setIntelligentBoxNumber(String intelligentBoxNumber) {
		this.intelligentBoxNumber = intelligentBoxNumber;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getProductOrigin() {
		return productOrigin;
	}
	public void setProductOrigin(String productOrigin) {
		this.productOrigin = productOrigin;
	}
	public CommodityType getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}
	
}
