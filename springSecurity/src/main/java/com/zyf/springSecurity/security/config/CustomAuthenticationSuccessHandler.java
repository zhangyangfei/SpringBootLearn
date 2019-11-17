package com.zyf.springSecurity.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/** 登录成功处理类 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("用户[" + authentication.getName() + "]已登录！");
//		request.getRequestDispatcher("/login").forward(request, response);
		// 重定向到登录成功页
		response.sendRedirect("/login/welcome");
	}

}
