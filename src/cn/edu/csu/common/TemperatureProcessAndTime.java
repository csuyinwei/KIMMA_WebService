package cn.edu.csu.common;

import java.util.ArrayList;

public class TemperatureProcessAndTime{
	
	public String getLastSubmitTimeOrFailInfo() {
		return lastSubmitTimeOrFailInfo;
	}

	public void setLastSubmitTimeOrFailInfo(String lastSubmitTimeOrFailInfo) {
		this.lastSubmitTimeOrFailInfo = lastSubmitTimeOrFailInfo;
	}



	String lastSubmitTimeOrFailInfo;
	
	ArrayList<TemperatureProcess> temperatureProcessList;
	
	
	
	public TemperatureProcessAndTime(){
		temperatureProcessList = new ArrayList<TemperatureProcess>();
	}

	public ArrayList<TemperatureProcess> getTemperatureProcessList() {
		return temperatureProcessList;
	}

	public void setTemperatureProcessList(
			ArrayList<TemperatureProcess> temperatureProcessList) {
		this.temperatureProcessList = temperatureProcessList;
	}



	public void add(TemperatureProcess tp) {
		temperatureProcessList.add(tp);
	}
}