package com.zyf.springMVC.mvcview;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
//@RequestMapping：请求路径与控制器的映射，并且在web服务器启动时扫描到HandlerMapping中。
@RequestMapping("/mvcview")
public class MvcViewController {
 
	@RequestMapping("/v1")
	public ModelAndView mvc1(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mvcview/v1");
		modelAndView.addObject("username", "张三");
		modelAndView.addObject("id", id);
		return modelAndView;
	}
	@RequestMapping("/v2")
	public String mvc2() {
		return "mvcview/v2";
	}
}