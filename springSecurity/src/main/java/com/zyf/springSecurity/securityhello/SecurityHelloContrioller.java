package com.zyf.springSecurity.securityhello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/securityhello")
public class SecurityHelloContrioller {

	/**
	 * 访问该路径，需要使用security自定生成的密码登录，用户名user。验证成功后进入sh1页面。
	 */
	// http://localhost:8080/securityhello/sh1
	@RequestMapping("/sh1")
	public String sh1() {
		return "securityhello/sh1";
	}
	
	@RequestMapping("/sh2")
	public String sh2() {
		return "securityhello/sh1";
	}
	@RequestMapping("/sh3")
	public String sh3() {
		return "securityhello/sh1";
	}
}
