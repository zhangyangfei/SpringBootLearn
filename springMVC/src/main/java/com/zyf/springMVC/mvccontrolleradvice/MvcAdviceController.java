package com.zyf.springMVC.mvccontrolleradvice;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 控制器通知测试 */
@Controller
@RequestMapping("/mvccontrolleradvice")
public class MvcAdviceController {

	// http://localhost:8080/mvccontrolleradvice/mca1
	@RequestMapping("/mca1")
	public ModelAndView mca1(int id, ModelAndView mav) {
		mav.setViewName("mvccontrolleradvice/mca1");
		return mav;
	}

	// http://localhost:8080/mvccontrolleradvice/mca2?date=2019-10-01
	@RequestMapping("/mca2")
	public ModelAndView mca2(Date date, ModelAndView mav) {
		mav.setViewName("mvccontrolleradvice/mca1");
		return mav;
	}
}
