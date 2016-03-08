package cn.edu.csu.controller.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.TemperatureData;
import cn.edu.csu.pojo.TransportProcess;
import cn.edu.csu.service.CommodityTransportService;
import cn.edu.csu.service.TemperatureDataService;
import cn.edu.csu.service.TransportProcessService;

@Controller
@RequestMapping("/phone/trans_proc")
public class TransportProcessControllerForPhone {
	
	@Autowired
	private TransportProcessService transportProcessService;
	
	@Autowired
	private CommodityTransportService commodityTransportService;
	
	@Autowired
	private TemperatureDataService temperatureDataService;

	public TransportProcessService getTransportProcessService() {
		return transportProcessService;
	}

	public void setTransportProcessService(
			TransportProcessService transportProcessService) {
		this.transportProcessService = transportProcessService;
	}

	public CommodityTransportService getCommodityTransportService() {
		return commodityTransportService;
	}

	public void setCommodityTransportService(
			CommodityTransportService commodityTransportService) {
		this.commodityTransportService = commodityTransportService;
	}
	
	public TemperatureDataService getTemperatureDataService() {
		return temperatureDataService;
	}

	public void setTemperatureDataService(
			TemperatureDataService temperatureDataService) {
		this.temperatureDataService = temperatureDataService;
	}

	
	
	//http://localhost:8080/cclms/phone/trans_proc/doDepart?intelligentBoxNumber=54:4A:16:48:B6:C7&departurePlace=X
	//@RequestMapping(value = "/doDepart")
	@RequestMapping(value = "/doDepart", method = RequestMethod.POST)
	@ResponseBody
	public String doDepart(String intelligentBoxNumber, String departurePlace){
		CommodityTransport commodityTransport =commodityTransportService.getCurrentCommodityTransportByIBNumber(intelligentBoxNumber);
		System.out.println(commodityTransport);
		if(commodityTransport==null){
			return "illegal_ib_number";
		}
		if(transportProcessService.getNullArrivalTransportProcess(commodityTransport)!=null){
			return "duplicate_depart";
		}
		transportProcessService.inputDepartureInfo(commodityTransport, departurePlace);
		return "success";
	}
	
	
	
	//http://localhost:8080/cclms/phone/trans_proc/doArrive?intelligentBoxNumber=54:4A:16:48:B6:C7&arrivalPlace=X&temperature=1,3
	//@RequestMapping(value = "/doArrive")
	@RequestMapping(value = "/doArrive", method = RequestMethod.POST)
	@ResponseBody
	public String doArrive(String intelligentBoxNumber, String arrivalPlace,String temperature){
		CommodityTransport comdtyTrans =commodityTransportService.getCurrentCommodityTransportByIBNumber(intelligentBoxNumber);
		if(comdtyTrans==null){
			return "illegal_ib_number";
		}
		TransportProcess transProc = transportProcessService.getNullArrivalTransportProcess(comdtyTrans);
		if(transProc==null){
			return "duplicate_arrive";
		}
		//保存温度信息
		TemperatureData temperatureData = new TemperatureData();
		temperatureData.setCommodityTransport(comdtyTrans);
		temperatureData.setTemperature(temperature);
		temperatureDataService.inputTemperatureData(temperatureData);
		//保存到达站点名称
		transportProcessService.inputArrivalInfo(transProc, arrivalPlace);
		return "success";
	}
}
