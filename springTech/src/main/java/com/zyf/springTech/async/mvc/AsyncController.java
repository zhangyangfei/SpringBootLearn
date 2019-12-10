package com.zyf.springTech.async.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyf.springTech.async.service.AsyncService;

@Controller
@RequestMapping(path="/asyncmvc")
public class AsyncController {

	@Autowired
	private AsyncService asyncService;
	
	// http://localhost:8080/asyncmvc/ma1
	@RequestMapping(value="/ma1")
	public String am1(ModelAndView mav){
		int num = 1;
		while (num <= 10) {
			asyncService.asynctest(Thread.currentThread().getName() + "");
			num++;
		}
		return "asyncmvc/ma1";
	}
}
