package cn.edu.csu.pojo;

import java.io.Serializable;

public class Vender implements Serializable {
	
	private int 	  	id;
	private String 		venderNumber;
	private String 		venderType;
	private int	  		salverQuantity;
	private int 	 	channelQuantity;
	private int 	 	capacityPerChannel;
	private int 	 	status;
	private int 		isDelete;
	
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVenderNumber() {
		return venderNumber;
	}
	public void setVenderNumber(String venderNumber) {
		this.venderNumber = venderNumber;
	}
	public String getVenderType() {
		return venderType;
	}
	public void setVenderType(String venderType) {
		this.venderType = venderType;
	}
	public int getSalverQuantity() {
		return salverQuantity;
	}
	public void setSalverQuantity(int salverQuantity) {
		this.salverQuantity = salverQuantity;
	}
	public int getChannelQuantity() {
		return channelQuantity;
	}
	public void setChannelQuantity(int channelQuantity) {
		this.channelQuantity = channelQuantity;
	}
	public int getCapacityPerChannel() {
		return capacityPerChannel;
	}
	public void setCapacityPerChannel(int capacityPerChannel) {
		this.capacityPerChannel = capacityPerChannel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
