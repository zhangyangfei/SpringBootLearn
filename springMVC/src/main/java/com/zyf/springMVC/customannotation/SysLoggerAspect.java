package com.zyf.springMVC.customannotation;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/** 注解@SysLogger的AOP */
@Aspect
@Component
public class SysLoggerAspect {

	private Logger logger;


	/** 日志主题 */
	private String logTheme;

	/** 切点 */
	@Pointcut(value = "@annotation(SysLogger)")
	public void pointCut() {
	}

	/** 环绕通知 */
	@Around("pointCut()")
	public void around(ProceedingJoinPoint pj) {
		Long start = System.currentTimeMillis();
		getLogger(pj).info(getLogTheme(pj) + "...开始。");
		try {
			// 显式回调目标对象的原有方法
			pj.proceed();
		} catch (Throwable e) {
			getLogger(pj).info(getLogTheme(pj) + "...异常：" + e.getMessage());
			e.printStackTrace();
		}
		getLogger(pj).info(getLogTheme(pj) + "...结束。");
		getLogger(pj).info(getLogTheme(pj) + "...耗时=" + (System.currentTimeMillis() - start) + "毫秒");
	}

	/** 获取日志记录对象 */
	private Logger getLogger(JoinPoint point) {
		if (null != logger) {
			return logger;
		}
		Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
		return logger;
	}

	/** 获取日志主题 */
	private String getLogTheme(JoinPoint point) {
		if (!StringUtils.isEmpty(logTheme)) {
			return logTheme;
		}
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		SysLogger annotation = method.getAnnotation(SysLogger.class);
		String value = annotation.value();
		return value;
	}
}
