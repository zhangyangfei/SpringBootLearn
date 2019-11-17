package com.zyf.springSecurity.security.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/** 登录页 */
	@RequestMapping("/login")
	public String page() {
		return "login/login";
	}

	/** 登录成功页 */
	@RequestMapping("/login/welcome")
	public String welcome() {
		return "login/welcome";
	}

	/** 登录失败页 */
	@RequestMapping("/login/fail")
	public String fail() {
		return "login/fail";
	}
	
}
