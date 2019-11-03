package com.zyf.springMVC.customannotation;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysLoggerAspect {

	private Logger logger;

	private String logTheme;
	
	@Pointcut(value = "@annotation(SysLogger)")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void before(JoinPoint point) {
		getLogger().info(getLogTheme(point)+"...开始。");
	}

	@After("pointCut()")
	public void after(JoinPoint point) {
		getLogger().info(getLogTheme(point)+"...结束。");
	}
	
	@AfterThrowing("pointCut()")
	public void afterThrowing(JoinPoint point) {
		getLogger().info(getLogTheme(point)+"...异常结束。");
	}
	private Logger getLogger() {
		if (null != logger) {
			return logger;
		}
		Logger logger = LoggerFactory.getLogger(this.getClass());
		return logger;
	}
	
	private String getLogTheme(JoinPoint point) {
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		SysLogger annotation = method.getAnnotation(SysLogger.class);
		String value = annotation.value();
		return value;
	}
}
