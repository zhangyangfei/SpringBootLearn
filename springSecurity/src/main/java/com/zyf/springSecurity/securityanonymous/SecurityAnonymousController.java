package com.zyf.springSecurity.securityanonymous;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 可匿名访问的请求 */
@Controller
@RequestMapping("/securityanonymous")
public class SecurityAnonymousController {

	// http://localhost:8080/securityanonymous/sa1
	@RequestMapping("/sa1")
	public String sa1() {
		return "securityanonymous/sa1";
	}

}
