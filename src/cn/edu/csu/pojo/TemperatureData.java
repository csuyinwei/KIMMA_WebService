package cn.edu.csu.pojo;

import java.io.Serializable;
import java.util.Date;

public class TemperatureData implements Serializable {
	
	private int 		  id;
	private CommodityTransport		  commodityTransport;
	private String	  	  temperature;
	private Date  	  	  submitTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CommodityTransport getCommodityTransport() {
		return commodityTransport;
	}
	public void setCommodityTransport(CommodityTransport commodityTransport) {
		this.commodityTransport = commodityTransport;
	}
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	
	
	
}
