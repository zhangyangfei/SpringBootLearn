package com.zyf.springMVC.common.customannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解：数据验证器
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidLogic {

	/**
	 * 注解名称
	 */
	String name() default "数据验证器";
	
	/**
	 * 验证器集合 
	 */
	Class<?>[] validators() default{};

}
