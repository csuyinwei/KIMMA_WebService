package cn.edu.csu.pojo;

import java.io.Serializable;
import java.util.Date;

public class VenderInventory implements Serializable {
	
	private int 		 id;
	private Vender 		 vender;
	private int 		 salverNumber;
	private int 		 channelNumber;
	private CommodityTransport	 		 commodityTransport;
	private int 	 	 inventoryQuantity;
	private Date 		 supplementTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Vender getVender() {
		return vender;
	}
	public void setVender(Vender vender) {
		this.vender = vender;
	}
	public int getSalverNumber() {
		return salverNumber;
	}
	public void setSalverNumber(int salverNumber) {
		this.salverNumber = salverNumber;
	}
	public int getChannelNumber() {
		return channelNumber;
	}
	public void setChannelNumber(int channelNumber) {
		this.channelNumber = channelNumber;
	}
	public CommodityTransport getCommodityTransport() {
		return commodityTransport;
	}
	public void setCommodityTransport(CommodityTransport commodityTransport) {
		this.commodityTransport = commodityTransport;
	}
	public int getInventoryQuantity() {
		return inventoryQuantity;
	}
	public void setInventoryQuantity(int inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}
	public Date getSupplementTime() {
		return supplementTime;
	}
	public void setSupplementTime(Date supplementTime) {
		this.supplementTime = supplementTime;
	}
	
	
}
