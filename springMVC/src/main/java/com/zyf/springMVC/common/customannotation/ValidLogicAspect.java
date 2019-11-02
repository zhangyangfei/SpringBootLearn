package com.zyf.springMVC.common.customannotation;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zyf.springMVC.mvcparamvalidate.UserValidator;

@Aspect
@Component
public class ValidLogicAspect {

	
	// 被@ValidLogic注解修饰的方法被调用（必须是public修饰，不能是private）时，进入该切入点
	@Pointcut(value = "@annotation(ValidLogic)")
	// 怎样让注解标注类和参数后，也进入该切点？ TODO
//	@Pointcut(value = "within(@org.springframework.stereotype.Controller *) && @annotation(ValidLogic)")//无效
	public void pointCut() {
	}

	@Before("pointCut()")
	public void before(JoinPoint point) throws InstantiationException, IllegalAccessException {
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		ValidLogic annotation = method.getAnnotation(ValidLogic.class);
		// 注解的value参数
		String value = annotation.name();
		System.out.println("value=" + value);

		// 注解的validators参数数组
		Class<?>[] validators = annotation.validators();
		Class<?> validator = validators[0];
		// 通过class获取对象实例
		Object obj = validator.newInstance();
		// 强转为UserValidator
		UserValidator userValidator = (UserValidator) obj;

		//如何获取WebDataBinder对象 TODO
//		WebDataBinder binder = applicationContext.getBean(WebDataBinder.class);
//		binder.addValidators(new UserValidator());
		Validator[] xxx= {};
//		binder.addValidators(xxx);
//		validators= {new UserValidator()};
	}
	/**
	 * 执行顺序
	 * 	controller的@InitBinder方法
	 * 	切面@Before方法
	 *  被注解@ValidLogic标注的方法
	 */
}
