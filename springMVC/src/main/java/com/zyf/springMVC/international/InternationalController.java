package com.zyf.springMVC.international;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 国际化 */
@Controller
@RequestMapping("/international")
public class InternationalController {

	@Autowired
	private MessageSource messageSource;

	// http://localhost:8080/international/ic1
	@RequestMapping("/ic1")
	public ModelAndView ic1(ModelAndView mav, HttpServletRequest request) {
		Locale locale = LocaleContextHolder.getLocale();
		System.out.println("国际化区域=" + locale);
		String msg = messageSource.getMessage("msg", null, locale);
		System.out.println("消息=" + msg);
		mav.addObject("msg", msg);
		mav.addObject("language", locale);
		mav.setViewName("international/ic1");
		return mav;
	}

}
