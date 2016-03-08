package cn.edu.csu.controller.phone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.CommodityType;
import cn.edu.csu.service.CommodityTransportService;
import cn.edu.csu.service.CommodityTypeService;

@Controller
@RequestMapping("/phone/comdty_trans")
public class CommodityTransportControllerForPhone {
	
	private static Logger logger = Logger.getLogger(CommodityTransportControllerForPhone.class);
	
	@Autowired
	private CommodityTransportService commodityTransportService;
	
	@Autowired
	private CommodityTypeService commodityTypeService;
	
	
	public CommodityTransportService getCommodityTransportService() {
		return commodityTransportService;
	}


	public void setCommodityTransportService(
			CommodityTransportService commodityTransportService) {
		this.commodityTransportService = commodityTransportService;
	}


	public CommodityTypeService getCommodityTypeService() {
		return commodityTypeService;
	}


	public void setCommodityTypeService(CommodityTypeService commodityTypeService) {
		this.commodityTypeService = commodityTypeService;
	}


	
	@RequestMapping(value = "/doInput", method = RequestMethod.POST)
	@ResponseBody
	public String input(String barcode, CommodityTransport commodityTransport) {
		CommodityType commodityType = commodityTypeService.getCommodityTypeByBarcode(barcode);
		if(commodityType==null){
			logger.info("illegal_barcode");
			return "illegal_barcode";
		}
		String ibNumber = commodityTransport.getIntelligentBoxNumber();
		if(ibNumber==null || "".equals(ibNumber)){
			logger.info("ib_number_null");
			return "ib_number_null";
		}
		String prod_date = commodityTransport.getProductDate();
		if(prod_date==null || "".equals(prod_date)){
			logger.info("production_date_null");
			return "production_date_null";
		}
		String prod_origin = commodityTransport.getProductOrigin();
		if(prod_origin==null || "".equals(prod_origin)){
			logger.info("production_origin_null");
			return "production_origin_null";
		}
		commodityTransport.setCommodityType(commodityType);
		commodityTransportService.inputCommodityTransport(commodityTransport);
		logger.info("success");
		return "success";
	}
}
