package cn.edu.csu.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.edu.csu.common.VenderOperateResult;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.service.VenderService;

@Controller
@SessionAttributes({"admin"})
@RequestMapping("/vender")
public class VenderController {
	@Autowired
	private VenderService venderService;

	public VenderService getVenderService() {
		return venderService;
	}

	public void setVenderService(VenderService venderService) {
		this.venderService = venderService;
	}
	
	public boolean logined(Model model){
		return model.containsAttribute("admin");
	}

	
	
	/**
	 * 处理对展示所有售货机信息的页面的请求
	 */
//	@RequestMapping("/listAll")
//	public String listAll(Model model) {
//		List<Vender> venders = venderService.listAll();
//		model.addAttribute("venders",venders);
//		return "/vender/listAll";
//	}
	
	
	
	/**
	 * 分页显示售货机信息
	 */
	@RequestMapping(value = "/list")//, method = RequestMethod.POST)
	public String pagination(String search, Integer pageNum, Integer pageSize, 
			Model model, HttpServletResponse response) {
		//设置访问此页面禁用浏览器缓存
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		//对不合法的页面编号和页面大小进行处理，设置默认值
		if(search == null) search="";
		if(pageNum == null || pageNum < 1) pageNum = 1;
		if(pageSize == null || pageSize < 1) pageSize = 10;
		//计算分页总数
		long records_count = venderService.getSearchCount(search);
		long pages = records_count % pageSize == 0 ? records_count / pageSize : records_count / pageSize + 1;
		//如果页面编号>页面总数，设定页面编号为最后一页
		if(pageNum > pages) pageNum = (int)pages;
		//获取当前页面下的售货机信息
		List<Vender> venders = venderService.searchPagination(search, pageNum, pageSize);
		//返回页面
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("venders", venders);
		model.addAttribute("search",search);
		model.addAttribute("count", records_count);
		return "/vender/list";
	}
	
	
	
	/**
	 * 处理对售货机信息输入的页面的请求
	 */
	@RequestMapping("/input")
	public String input(Model model) {
		return "/vender/input";
	}
	
	
	
	/**
	 * 售货机信息输入Action
	 */
	@RequestMapping(value = "/doInput")//, method = RequestMethod.POST)
	@ResponseBody
	public VenderOperateResult doInput(Vender vender, Model model) {
		VenderOperateResult result = new VenderOperateResult();
		if(vender.getVenderNumber()==null || vender.getVenderNumber().equals("")){
			result.vender_number="售货机编号未填写！";
		}
		if(vender.getVenderType()==null || vender.getVenderType().equals("")){
			result.vender_type = "售货机类型未填写！";
		}
		if(vender.getSalverQuantity()==0){
			result.salver_quantity = "托盘数未填写！";
		}
		if(vender.getChannelQuantity()==0){
			result.channel_quantity = "货道数未填写！";
		}
		if(vender.getCapacityPerChannel()==0){
			result.capacity_per_channel = "货道容量未填写！";
		}
		if(vender.getStatus()==0){
			result.status = "售货机状态未填写！";
		}
		if(result.fail()){
			model.addAttribute("vender",vender);
			return result;
		}
		try{
			venderService.inputVender(vender);
		}catch(ConstraintViolationException e){
			result.vender_number="售货机编号重复！";
			model.addAttribute("vender",vender);
		}
		return result;
	}
	
	
	
	/**
	 * 处理对售货机信息更新入的页面的请求
	 */
	@RequestMapping("/update")
	public String toUpdate(String venderNumber, Model model) {
		Vender vender = venderService.getVenderByNumber(venderNumber);
		model.addAttribute("vender", vender);
		return "/vender/update";
	}
	
	
	
	/**
	 * 售货机信息更新Action
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	@ResponseBody
	public VenderOperateResult doUpdate(Vender vender, Model model) {
		VenderOperateResult result = new VenderOperateResult();
		if(vender.getVenderNumber()==null || vender.getVenderNumber().equals("")){
			result.vender_number="售货机编号未填写！";
		}
		if(vender.getVenderType()==null || vender.getVenderType().equals("")){
			result.vender_type = "售货机类型未填写！";
		}
		if(vender.getSalverQuantity()==0){
			result.salver_quantity = "托盘数未填写！";
		}
		if(vender.getChannelQuantity()==0){
			result.channel_quantity = "货道数未填写！";
		}
		if(vender.getCapacityPerChannel()==0){
			result.capacity_per_channel = "货道容量未填写！";
		}
		if(vender.getStatus()==0){
			result.status = "售货机状态未填写！";
		}
		if(result.fail()){
			model.addAttribute("vender",vender);
			return result;
		}
		try{
			venderService.updateVender(vender);
		}catch(ConstraintViolationException e){
			result.vender_number="售货机编号重复！";
			model.addAttribute("vender",vender);
		}
		return result;
	}
		
	
	
	/**
	 * 售货机信息删除Action
	 */
	@RequestMapping("/doDelete")
	@ResponseBody
	public String deleteByVenderNumber(@RequestParam String venderNumber, Model model) {
		try{
			venderService.deleteByVenderNumber(venderNumber);
		}catch(ConstraintViolationException e){
			return "failed";
		}
		return "success";
	}
	
	
	
	/**
	 * 搜索
	 */
	@RequestMapping("/doSearch")
	public String search(String venderNum, Model model) {
		Vender vender = venderService.getVenderByNumber(venderNum);
		model.addAttribute("vender", vender);
		return "/vender/searchResult";
	}
}
