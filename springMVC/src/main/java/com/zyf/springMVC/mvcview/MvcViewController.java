package com.zyf.springMVC.mvcview;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping("/mvcview")
public class MvcViewController {
 
	@RequestMapping("/v1")
	public ModelAndView mvc1() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mvcview/v1");
		modelAndView.addObject("username", "张三");
		return modelAndView;
	}
	@RequestMapping("/v2")
	public String mvc2() {
		return "mvcview/v2";
	}
}