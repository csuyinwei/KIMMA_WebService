package cn.edu.csu.controller.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.pojo.VenderInventory;
import cn.edu.csu.service.CommodityTransportService;
import cn.edu.csu.service.VenderInventoryService;
import cn.edu.csu.service.VenderService;
import cn.edu.csu.util.SalverStringToIntAdapter;

@Controller
@RequestMapping("/phone/vender_inv")
public class VenderInventoryControllerForPhone {
	
	@Autowired
	private VenderInventoryService venderInventoryService;
	
	@Autowired
	private VenderService venderService;
	
	@Autowired
	private CommodityTransportService commodityTransportService;
	
	public VenderInventoryService getVenderInventoryService() {
		return venderInventoryService;
	}

	public void setVenderInventoryService(
			VenderInventoryService venderInventoryService) {
		this.venderInventoryService = venderInventoryService;
	}

	
	
	@RequestMapping(value = "/doSupply", method = RequestMethod.POST)
	@ResponseBody
	public String supply(String venderNumber, String ibNumber, String salver, VenderInventory venderInventory) {
		//检查是否存在该售货机编号
		Vender vender = venderService.getVenderByNumber(venderNumber);
		if(vender==null ){
			return "illegal_vender_number";
		}
		//检查是否存在该智能箱编号
		CommodityTransport commodityTransport = commodityTransportService.getCurrentCommodityTransportByIBNumber(ibNumber);
		if(commodityTransport==null){
			return "illegal_ib_number";
		}
		//检查托盘号是否超出售货机最大托盘数
		int salverNumber = SalverStringToIntAdapter.parse(salver);
		if(salverNumber > vender.getSalverQuantity()){
			return "illegal_salver_number";
		}
		//检查货道号是否超出售货机最大货道数
		int channelNumber = venderInventory.getChannelNumber();
		if(channelNumber > vender.getChannelQuantity()){
			return "illegal_channel_number";
		}
		venderInventory.setSalverNumber(salverNumber);
		venderInventory.setVender(vender);
		venderInventory.setCommodityTransport(commodityTransport);
		venderInventoryService.supply(venderInventory);
		return "success";
	}
}
