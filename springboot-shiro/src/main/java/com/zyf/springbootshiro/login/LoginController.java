package com.zyf.springbootshiro.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	/** 登录表单提交 */
	@RequestMapping(value = "/login/action")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前准备 token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 执行认证登陆
		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			return "未知账户";
		} catch (IncorrectCredentialsException ice) {
//			return "密码不正确";
			return "/login/fail";
		} catch (LockedAccountException lae) {
			return "账户已锁定";
		} catch (ExcessiveAttemptsException eae) {
			return "用户名或密码错误次数过多";
		} catch (AuthenticationException ae) {
//			return "用户名或密码不正确！";
			return "/login/fail";
		}
		if (subject.isAuthenticated()) {
			return "/login/welcome";
		} else {
			token.clear();
			return "/login/fail";
		}
	}
}
