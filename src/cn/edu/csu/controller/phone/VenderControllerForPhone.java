package cn.edu.csu.controller.phone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.csu.common.VenderNumberList;
import cn.edu.csu.service.VenderService;

@Controller
@RequestMapping("/phone/vender")
public class VenderControllerForPhone {
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

	
	@RequestMapping("/doListAll")
	@ResponseBody
	public VenderNumberList listAll(Model model) {
		List<String> list = venderService.listAllVenderNumber();
		VenderNumberList venderNumberList = new VenderNumberList();
		venderNumberList.setVenderNumbers(list);
		return venderNumberList;
	}
}
