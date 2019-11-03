package com.zyf.springMVC.customannotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customannotation")
public class CustomAnnotationController {

	@RequestMapping("/ca1")
	@SysLogger(value="自定义注解测试")
	public ModelAndView ca1(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("customannotation/ca1");
		modelAndView.addObject("username", "张三");
		modelAndView.addObject("id", id);
		if(0==id){
			throw new RuntimeException("用户id不能为0");
		}
		return modelAndView;
	}
}