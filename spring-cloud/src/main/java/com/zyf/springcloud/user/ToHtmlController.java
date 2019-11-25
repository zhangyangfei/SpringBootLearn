package com.zyf.springcloud.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ToHtmlController {
	@RequestMapping("/userindex")
	public String init() {
		return "user/userindex";
	}
}
