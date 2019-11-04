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

	/** ApplicationContext对象初始化 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}

	/** 获取ApplicationContext对象 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/** 根据beanname获取对象 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	/** 根据类型获取对象 */
	public static Object getBean(Class<?> clazz) {
		return applicationContext.getBean(clazz);
	}

	/** 获取有指定注解的对象集合 */
	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
		return applicationContext.getBeansWithAnnotation(annotationType);
	}

	/** 获取有指定类型的对象集合 */
	public static Map<String, ?> getBeansOfType(Class<?> type) {
		return applicationContext.getBeansOfType(type);
	}
}
