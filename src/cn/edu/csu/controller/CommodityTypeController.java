package cn.edu.csu.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.edu.csu.common.CommodityTypeOperateResult;
import cn.edu.csu.pojo.CommodityType;
import cn.edu.csu.service.CommodityTypeService;

@Controller
@SessionAttributes({"admin"})
@RequestMapping("/comdty_type")
public class CommodityTypeController {
	@Autowired
	private CommodityTypeService commodityTypeService;

	public CommodityTypeService getComdtyTypeService() {
		return commodityTypeService;
	}

	public void setComdtyTypeService(CommodityTypeService commodityTypeService) {
		this.commodityTypeService = commodityTypeService;
	}
	
	public boolean logined(Model model){
		return model.containsAttribute("admin");
	}
	
	
	
	/**
	 * 处理对商品类型录入页面的请求，
	 */
	@RequestMapping("/input")
	public String input(Model model) {
		return "/comdty_type/input";
	}
	
	
	
	/**
	 * 商品类型录入的Action
	 */
	@RequestMapping(value = "/doInput", method = RequestMethod.POST)
	@ResponseBody
	public CommodityTypeOperateResult doInput(CommodityType comdtyType, Model model) {

		CommodityTypeOperateResult result = new CommodityTypeOperateResult();
		if(comdtyType.getCommodityName()==null || comdtyType.getCommodityName().equals("")){
			result.commodity_name = "商品名称未填写！";
		}
		if(comdtyType.getBarcode()==null || comdtyType.getBarcode().equals("")){
			result.barcode= "条形码未填写！";
		}
		if(comdtyType.getBrandName()==null || comdtyType.getBrandName().equals("")){
			result.brand_name = "商品品牌未填写！";
		}
		if(comdtyType.getShelfLife()==null || comdtyType.getShelfLife().equals("")){
			result.shelf_life = "保质期未填写！";
		}
		if(comdtyType.getRemark()==null || comdtyType.getRemark().equals("")){
			comdtyType.setRemark(null);
		}
		if(result.fail()){
			model.addAttribute("comdtyType",comdtyType);
			return result;
		}
		try{
			commodityTypeService.inputCommodityType(comdtyType);
		}catch(ConstraintViolationException e){
			result.barcode = "条形码重复！";
			model.addAttribute("comdtyType",comdtyType);
		}
		return result;
	}
	
	
	
//	/**
//	 * 处理对展示所有商品类型的页面的请求
//	 */
//	@RequestMapping("/listAll")
//	public String listAll(Model model) {
//		List<CommodityType> comdtyTypes = commodityTypeService.listAll();
//		model.addAttribute("comdty_types", comdtyTypes);
//		return "/comdty_type/listAll";
//	}
	

	/**
	 * 分页显示商品
	 */
	@RequestMapping(value = "/list")//, method = RequestMethod.POST)
	public String pagination(String search, Integer pageNum, Integer pageSize,
			Model model, HttpServletResponse response) {
		//设置访问此页面禁用浏览器缓存
		
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", -1);

		//对不合法的页面编号和页面大小进行处理，设置默认值
		if(search == null) search="";
		if(pageNum == null || pageNum < 1) pageNum = 1;
		if(pageSize == null || pageSize < 1) pageSize = 10;
		//计算分页总数
		long records_count = commodityTypeService.getSearchCount(search);
		long pages = records_count % pageSize == 0 ? records_count / pageSize : records_count / pageSize + 1;
		//如果页面编号>页面总数，设定页面编号为最后一页
		if(pageNum > pages) pageNum = (int)pages;
		//获取当前页面下的售货机信息
		List<CommodityType> comdtyTypes = commodityTypeService.searchPagination(search,pageNum,pageSize);
		//返回页面
		model.addAttribute("count", records_count);
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("comdty_types", comdtyTypes);
		model.addAttribute("search",search);
		return "/comdty_type/list";
	}
	
	
	
	/**
	 * 处理对更新商品类型的页面的请求
	 */
	@RequestMapping("/update")
	public String toUpdate(String barcode, Model model) {
		CommodityType commodityType = commodityTypeService.getCommodityTypeByBarcode(barcode);
		model.addAttribute("commodityType", commodityType);
		return "/comdty_type/update";
	}
	
	
	
	/**
	 * 商品类型更新的Action
	 */
	@RequestMapping(value="/doUpdate", method = RequestMethod.POST)
	@ResponseBody
	public CommodityTypeOperateResult update(CommodityType comdtyType, Model model) {
		CommodityTypeOperateResult result = new CommodityTypeOperateResult();
		if(comdtyType.getCommodityName()==null || comdtyType.getCommodityName().equals("")){
			result.commodity_name = "商品名称未填写！";
		}
		if(comdtyType.getBarcode()==null || comdtyType.getBarcode().equals("")){
			result.barcode= "条形码未填写！";
		}
		if(comdtyType.getBrandName()==null || comdtyType.getBrandName().equals("")){
			result.brand_name = "商品品牌未填写！";
		}
		if(comdtyType.getShelfLife()==null || comdtyType.getShelfLife().equals("")){
			result.shelf_life = "保质期未填写！";
		}
		if(comdtyType.getRemark()==null || comdtyType.getRemark().equals("")){
			comdtyType.setRemark(null);
		}
		if(result.fail()){
			model.addAttribute("comdtyType",comdtyType);
			return result;
		}
		try{
			commodityTypeService.updateCommodityType(comdtyType);
		}catch(ConstraintViolationException e){
			result.barcode = "条形码重复！";
			model.addAttribute("comdtyType",comdtyType);
		}
		return result;
	}
	
	
	
	/**
	 * 删除商品类型的Action
	 */
	@RequestMapping("/doDelete")
	@ResponseBody
	public String deleteByBarcode(String barcode, Model model) {
		try{
			commodityTypeService.deleteCommodityTypeByBarcode(barcode);
		}catch(ConstraintViolationException e){
			return "failed";
		}
		return "success";
	}
	
	
	
	/**
	 * 搜索
	 */
	@RequestMapping("/doSearch")
	public String search(String comdtyName, Model model) {
		List<CommodityType> comdtyTypes = commodityTypeService.searchByCommodityName(comdtyName);
		model.addAttribute("comdtyTypes", comdtyTypes);
		return "/comdty_type/searchResult";
	}
}
