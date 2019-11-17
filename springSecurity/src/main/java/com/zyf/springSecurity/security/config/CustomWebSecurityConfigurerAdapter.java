package com.zyf.springSecurity.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.zyf.springSecurity.security.utils.EncryptUtil;

/** security配置：用户认证、权限认证 */
@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
//	@Autowired
//	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	// 用户认证配置，使用user-detail机制
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 认证服务注册userDetailsService
		// spring5的security必须使用密码编码器，否则抛出异常
		auth.userDetailsService(userDetailsService).passwordEncoder(EncryptUtil.getEncoder());
	}

	// 请求认证配置，权限访问策略由FilterSecurityInterceptor处理
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().authenticated()//所有请求都要验证
			.and().formLogin()// 使用默认from登录
			.loginPage("/login") //自定义登录页（请求/login会返回视图，即自定义登录页面）
			.permitAll() //登录页面用户任意访问
			.loginProcessingUrl("/login/form")// 拦截url=/login/form的表单提交，取表单内username和password进行验证
			//.defaultSuccessUrl("/login/welcome")// 登录成功页，也可以在successHandler中处理
//			.failureUrl("/login/fail")// 登录失败页，也可以在failureHandler中处理
			.failureUrl("/login?error=true")//登录页获取后台错误消息【error=true重要】，如果配置failureHandler则会失效，原因不明 TODO
			.successHandler(customAuthenticationSuccessHandler)
//			.failureHandler(customAuthenticationFailureHandler)//废弃不用
			
			.and().logout()
			.logoutUrl("/logout")//登出请求url
			.logoutSuccessHandler(customLogoutSuccessHandler)
			.and().httpBasic();// 启动http基础验证

		 http.csrf().disable();// 禁用csrf，否则无法登录。如果不禁用，则要在form中提交防csrf的参数。
		
		// **************************** 添加自定义过滤器（即权限访问策略）****************************
		// http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);

	}
	
//	// 请求认证配置
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		String request1 = "/securityhello/sh1";
//		String role1 = "TEST";
//
//		String request2 = "/securityhello/sh2";
//		String role2 = "USER";
//
//		String request3 = "/securityhello/sh3";
//		
//		http.authorizeRequests()// 限定以下请求需要通过验证
//				.antMatchers(request1).hasAnyRole(role1,role2)// role1或者role2角色才能请求request1。（hasAnyRole会给角色字符串自动增加前缀ROLE_，所以构建用户详情的角色要匹配ROLE_TEST）
//				.antMatchers(request2).hasAnyAuthority("ROLE_"+role1,"ROLE_"+role2)// role1或者role2角色才能请求request2。（hasAnyAuthority不会给角色字符串自动增加前缀ROLE_）
//				.antMatchers(request3).denyAll()//请求request3禁止一切访问
//
//				.and().formLogin()//使用默认登录页面
//				.and().httpBasic();//启动http基础验证
//
//	}
}