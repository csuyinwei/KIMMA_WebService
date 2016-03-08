package cn.edu.csu.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TransportProcess implements Serializable {
	
	private int 		id;
	private CommodityTransport 		commodityTransport;
	private String    	departurePlace;
	private Date 		departureTime;
	private String		arrivalPlace;
	private Date		arrivalTime;
	
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
	public String getDeparturePlace() {
		return departurePlace;
	}
	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalPlace() {
		return arrivalPlace;
	}
	public void setArrivalPlace(String arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
