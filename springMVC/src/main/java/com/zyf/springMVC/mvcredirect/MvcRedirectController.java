package com.zyf.springMVC.mvcredirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/mvcredirect")
public class MvcRedirectController {

	@RequestMapping(value = "/mrd1")
	public ModelAndView mrd1(String name, ModelAndView mav) {
		mav.addObject("name", name);
		mav.setViewName("mvcredirect/mrd1");
		return mav;
	}

	// http://localhost:8080/mvcredirect/mrd2?name=zhangsan
	@RequestMapping(value = "/mrd2")
	public ModelAndView mrd2(String name, ModelAndView mav) {
		mav.addObject("name", name);
		// 重定向到mrd1
		mav.setViewName("redirect:/mvcredirect/mrd1");
		return mav;
	}

	// http://localhost:8080/mvcredirect/mrd3?name=李四
	@RequestMapping(value = "/mrd3")
	public ModelAndView mrd3(String name, RedirectAttributes ra, ModelAndView mav) {
		User user = new User();
		user.setName(name);
		// 重定向到mrd4，且将对象数据传递过去
		ra.addFlashAttribute("user", user);
		// mav.addObject("user", user); 此方法无法传递对象
		mav.setViewName("redirect:/mvcredirect/mrd4");
		return mav;
	}

	@RequestMapping(value = "/mrd4")
	public ModelAndView mrd4(User user, ModelAndView mav) {
		mav.addObject("user", user);
		mav.setViewName("mvcredirect/mrd4");
		return mav;
	}
}
