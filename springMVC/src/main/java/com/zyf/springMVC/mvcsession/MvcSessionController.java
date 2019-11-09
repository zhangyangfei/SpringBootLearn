package com.zyf.springMVC.mvcsession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/** 注解存取HttpSession中的参数 */
@Controller
@RequestMapping("/mvcsession")
// @SessionAttributes存入数据到session策略，指定参数名称或者参数类型，取二者并集。
@SessionAttributes(value = { "user" }, types = { User.class })
public class MvcSessionController {

	// 跳转到ms1页面
	// http://localhost:8080/mvcsession/ms1
	@RequestMapping("/ms1")
	public String ms1() {
		return "mvcsession/ms1";
	}

	// ajax请求，数据存入httpsession
	@RequestMapping("/ms2")
	public ModelAndView ms2(@RequestBody User user, ModelAndView mav) {
		user.setNote(user.getNote() + "_ms2");
		mav.addObject("user", user);
		mav.setViewName("mvcsession/ms1");
		return mav;// 配合@SessionAttributes，执行完改方法后，user存入httpsession中。
	}

	// @SessionAttribute从httpsession取出数据
	// http://localhost:8080/mvcsession/ms3
	@RequestMapping("/ms3")
	public ModelAndView ms3(@SessionAttribute(value = "user") User user, ModelAndView mav) {
		user.setNote(user.getNote() + "_ms3");
		mav.setViewName("mvcsession/ms1");
		return mav;
	}

	// ajax请求
	@RequestMapping("/ms2_1")
	@ResponseBody
	public User ms2_1(@RequestBody User user, ModelAndView mav) {
		user.setNote(user.getNote() + "_ms2");
		mav.addObject("user", user);
		mav.setViewName("mvcsession/ms1");
		return user;// 验证发现该方式未成功将user存入httpsession中。需要使用ms2的方式
	}
}
