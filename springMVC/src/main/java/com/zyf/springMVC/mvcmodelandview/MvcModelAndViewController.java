package com.zyf.springMVC.mvcmodelandview;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**数据模型*/
@Controller
@RequestMapping("/mvcmodelandview")
public class MvcModelAndViewController {

	@RequestMapping("mav1")
	public ModelAndView mav1(ModelAndView mav) {
		Map<String, Object> modelMap = new ModelMap();
		modelMap.put("name", "张三");
		mav.addAllObjects(modelMap);// ModelAndView保存map数据
		mav.addObject("id", "001");// ModelAndView保存键值数据
		User user = new User();
		user.setNote("备注信息");
		mav.addObject("user", user);// ModelAndView保存键值数据，值是一个对象
		mav.setViewName("mvcmodelandview/mav1");
		return mav;
	}

	@RequestMapping("mav2")
	public String mav2(Model model) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("name", "张三");
		model.addAllAttributes(modelMap);
		model.addAttribute("id", "001");
		User user = new User();
		user.setNote("备注信息");
		model.addAttribute("user", user);
		return "mvcmodelandview/mav1";
	}

	@RequestMapping("mav3")
	public String mav3(ModelMap modelMap) {
		modelMap.put("name", "李四");
		modelMap.addAttribute("id", "002");
		User user = new User();
		user.setNote("备注信息2");
		modelMap.addAttribute("user", user);
		return "mvcmodelandview/mav1";
	}

}
