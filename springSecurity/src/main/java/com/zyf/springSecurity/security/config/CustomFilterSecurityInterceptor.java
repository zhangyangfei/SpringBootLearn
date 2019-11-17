package com.zyf.springSecurity.security.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/** 自定义过滤器（即权限访问策略） */
@Component
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	// 路径拦截处理类
	@Autowired
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	// 权限决策处理类
	@Autowired
	public void setMyAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
		super.setAccessDecisionManager(accessDecisionManager);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		// fi里面有一个被拦截的url,里面调用InvocationSecurityMetadataSource的getAttributes(Object
		// object)这个方法获取fi对应的所有权限
		// 再调用AccessDecisionManager的decide方法来校验用户的权限是否足够
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			// 执行下一个拦截器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

}
