package com.zyf.springSecurity.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/** 自定义路径拦截处理类 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	/**
	 * 获取当前请求url所需角色集合
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 当前请求对象
		FilterInvocation fi = (FilterInvocation) object;
		System.out.println("当前请求路径=" + fi.getRequestUrl());
		// 获取当前路径所需要的角色（从数据查询，此处省略）
		List<ConfigAttribute> roles = new ArrayList<ConfigAttribute>();
		SecurityConfig role = new SecurityConfig("ROLE_TEST");
		roles.add(role);
		if (fi.getRequestUrl().contains("anonymous") || fi.getRequestUrl().contains("/login")) {
			role = new SecurityConfig("ROLE_ANONYMOUS");// 匿名角色可访问（security匿名访问时的角色默认为ROLE_ANONYMOUS）
			roles.add(role);
		}
		return roles;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return FilterInvocation.class.isAssignableFrom(aClass);
	}
}