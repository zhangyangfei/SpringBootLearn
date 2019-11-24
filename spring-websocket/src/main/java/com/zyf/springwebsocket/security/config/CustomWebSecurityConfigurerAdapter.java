package com.zyf.springwebsocket.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/** security配置：用户认证、权限认证 */
@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	// 用户认证配置，使用user-detail机制
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 认证服务注册userDetailsService
		// spring5的security必须使用密码编码器，否则抛出异常
		auth.userDetailsService(userDetailsService).passwordEncoder(EncryptUtil.getEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

		http.csrf().disable();// 禁用csrf，否则无法登录。如果不禁用，则要在form中提交防csrf的参数。

	}

}