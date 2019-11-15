package com.zyf.springSecurity.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/** security配置：用户认证、权限认证 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	// 用户认证配置，使用user-detail机制
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 认证服务注册userDetailsService
		// spring5的security必须使用密码编码器，否则抛出异常
		auth.userDetailsService(userDetailsService).passwordEncoder(EncryptUtil.getEncoder());
	}

	// 请求认证配置-批量
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<RequestRole> requestRoles = getRequestRole();
		for (RequestRole requestRole : requestRoles) {
			http.authorizeRequests()
				.antMatchers(requestRole.getUrl()).hasAnyRole(requestRole.getRoles())
				.and().formLogin()
				.and().httpBasic();
		}
	}
	
	// 请求-角色列表
	private List<RequestRole> getRequestRole(){
		String request1 = "/securityhello/sh1";
		String role1 = "TEST";
		String request2 = "/securityhello/sh2";
		String role2 = "USER";
		List<RequestRole> requestRoles =  new ArrayList<RequestRole>();
		RequestRole requestRole = new RequestRole();
		requestRole.setUrl(request1);
		requestRole.setRoles(new String[]{role1,role2});
		requestRoles.add(requestRole);
		requestRole = new RequestRole();
		requestRole.setUrl(request2);
		requestRole.setRoles(new String[]{role2});
		requestRoles.add(requestRole);
		return requestRoles;
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