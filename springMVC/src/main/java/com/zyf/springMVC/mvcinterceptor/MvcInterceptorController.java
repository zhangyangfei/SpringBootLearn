package com.zyf.springMVC.mvcinterceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/mvcinterceptor")
public class MvcInterceptorController {

	// http://localhost:8080/mvcinterceptor/mic1?id=1
	@RequestMapping(value = "/mic1")
	public ModelAndView mic1(@RequestParam(value = "id") int userid,ModelAndView modelAndView) {
		modelAndView.addObject("userid",userid); 
		modelAndView.setViewName("mvcinterceptor/mic1");
		System.out.println("执行控制器方法...mic1");
		return modelAndView;
	}
	
	@RequestMapping(value = "/mic2")
	public String mic2(@RequestParam(value = "id") int userid) {
		System.out.println("执行控制器方法...mic2");
		return "mvcinterceptor/mic1";
	}
}
