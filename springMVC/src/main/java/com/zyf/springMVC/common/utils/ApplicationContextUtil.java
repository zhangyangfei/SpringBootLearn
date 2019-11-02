package com.zyf.springMVC.common.utils;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具类
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {

		return applicationContext.getBeansWithAnnotation(annotationType);
	}

	public static Map<String, ?> getBeansOfType(Class<?> type) {
		return applicationContext.getBeansOfType(type);
	}
}
