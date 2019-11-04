package com.zyf.springMVC.mvcinterceptor;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**拦截器注册，需要实现WebMvcConfigurer的addInterceptors方法*/
@Configuration
public class MvcInterceptorConfig implements WebMvcConfigurer {

	/**
	 * 注册多个拦截器（拦截器方法的执行顺序是采用的责任链模式）
	 * preHandle先注册先执行、postHandle和afterCompletion是先注册后执行
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		InterceptorRegistration ir = registry.addInterceptor(new MvcInterceptor());
		// 注册拦截器的拦截请求路径
		List<String> pathList = Arrays.asList("/mvcinterceptor/*");
		ir.addPathPatterns(pathList);
		
		// 注册拦截器2
		InterceptorRegistration ir2 =registry.addInterceptor(new MvcInterceptor2());
		// 注册拦截器2的拦截请求路径
		List<String> pathList2 = Arrays.asList("/mvcinterceptor/*");
		ir2.addPathPatterns(pathList2);
	}
}
