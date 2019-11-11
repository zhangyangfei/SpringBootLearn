package com.zyf.springMVC.mvccontrolleradvice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/** 控制器的通知类 */
// 指定扫描的包和指定有哪些注解的类(取符合二者的并集)
//@ControllerAdvice(basePackages = { "com.zyf.springMVC.mvccontrolleradvice" })
//@ControllerAdvice(annotations = { Controller.class })
@ControllerAdvice(basePackages = { "com.zyf.springMVC.mvccontrolleradvice" },annotations = { Controller.class })
public class MvcControllerAdvice {

	// mvc异常处理，如果发送异常，进入该方法
	@ExceptionHandler(Exception.class)
	public String exception(Model model, Exception ecp) {
		model.addAttribute("exception_message", ecp.getMessage());
		ecp.printStackTrace();
		// 一般设置为共通error页面
		return "mvccontrolleradvice/mca2";
	}
	
	// 绑定格式化
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		// 注册日期转换器
		binder.registerCustomEditor(Date.class, customDateEditor);
		// 增加一个数据验证器
		// binder.addValidators(? extends Validator);
	}
	
	// 数据模型增加参数，在执行控制器方法前执行。三种数据模型用任意一个都可以。
	@ModelAttribute
	public void addAttribute(/*Model model,ModelAndView mav,*/ModelMap modelMap){
//		model.addAttribute("project_name", "springmvc");
//		mav.addObject("project_name", "springmvc");
		modelMap.put("project_name", "springmvc");
	}
}
