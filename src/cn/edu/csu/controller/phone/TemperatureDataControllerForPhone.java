package cn.edu.csu.controller.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.csu.common.TemperatureProcessAndTime;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.service.TemperatureDataService;
import cn.edu.csu.service.VenderInventoryService;
import cn.edu.csu.service.VenderService;
import cn.edu.csu.util.SalverStringToIntAdapter;

@Controller
@RequestMapping("/phone/temperature")
public class TemperatureDataControllerForPhone {
	
	@Autowired
	private TemperatureDataService temperatureDataService;
	
	@Autowired
	private VenderInventoryService venderInventoryService;
	
	@Autowired
	private VenderService venderService;

	public VenderService getVenderService() {
		return venderService;
	}

	public void setVenderService(VenderService venderService) {
		this.venderService = venderService;
	}

	public TemperatureDataService getTemperatureDataService() {
		return temperatureDataService;
	}

	public void setTemperatureDataService(
			TemperatureDataService temperatureDataService) {
		this.temperatureDataService = temperatureDataService;
	}

	public VenderInventoryService getVenderInventoryService() {
		return venderInventoryService;
	}

	public void setVenderInventoryService(
			VenderInventoryService venderInventoryService) {
		this.venderInventoryService = venderInventoryService;
	}

	public boolean logined(Model model){
		return model.containsAttribute("admin");
		
	}
	
	
	
	//http://localhost:8080/cclms/phone/temperature/getProcess?venderNumber=V816812732&salverNumber=A&channelNumber=1
	@RequestMapping("/getProcess")
	@ResponseBody                           
	public TemperatureProcessAndTime getTemperatureDate(String venderNumber,String salverNumber,int channelNumber, Model model) {
		TemperatureProcessAndTime tmp = new TemperatureProcessAndTime();
		
		
		Vender vender = venderService.getVenderByNumber(venderNumber);
		//判断venderNumber对应的售货机是否存在
		if(vender==null){
			tmp.setLastSubmitTimeOrFailInfo("illegal_vender_number");
			return tmp;
		}
		//判断托盘号是否合法
		int salverNumberAdapted = SalverStringToIntAdapter.parse(salverNumber);
		if(salverNumberAdapted>vender.getSalverQuantity()){
			tmp.setLastSubmitTimeOrFailInfo("illegal_salver_number");
			return tmp;
		}
		//判断货道号是否合法
		if(channelNumber>vender.getChannelQuantity()){
			tmp.setLastSubmitTimeOrFailInfo("illegal_channel_number");
			return tmp;
		}
		//判断该货道上是否有货物
		CommodityTransport comdtyTrans = venderInventoryService.getTheFrontestCommodityByVSC(vender, salverNumberAdapted, channelNumber);
		if(comdtyTrans==null){
			tmp.setLastSubmitTimeOrFailInfo("none_commodity");
			return tmp;
		}
		TemperatureProcessAndTime tpt = temperatureDataService.getTemperatureProcess(comdtyTrans, 10);
		if(tpt==null){
			tmp.setLastSubmitTimeOrFailInfo("none_temperature_process");
			return tmp;
		}
		return tpt;
	}
}



