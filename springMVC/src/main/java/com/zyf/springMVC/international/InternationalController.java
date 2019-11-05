package com.zyf.springMVC.international;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/international")
public class InternationalController {

	@Autowired
	private MessageSource messageSource;

	//http://localhost:8080/international/ic1?language=en_US
	@RequestMapping("/ic1")
	public ModelAndView ic1(ModelAndView mav) {
		Locale locale = LocaleContextHolder.getLocale();
		String msg = messageSource.getMessage("msg", null, locale);
		System.out.println(msg);
		mav.addObject("msg", msg);
		mav.setViewName("international/ic1");
		return mav;
	}

}
