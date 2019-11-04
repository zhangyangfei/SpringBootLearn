package com.zyf.springMVC.common.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.zyf.springMVC.common.customannotation.DataValidator;
import com.zyf.springMVC.common.utils.ApplicationContextUtil;

/**
 * controller类的通知
 */
//@ControllerAdvice(basePackages = { "com.zyf.springMVC.mvcparamvalidate" }, annotations = Controller.class)
public class CommonControllerAdvice {
	@SuppressWarnings("rawtypes")
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 已绑定的验证器
		List<Validator> validators = binder.getValidators();
		// 已绑定的验证器类型集合
		List<Class> validatorsClass = new ArrayList<Class>();
		for (Validator val : validators) {
			validatorsClass.add(val.getClass());
		}
		// 获取所有被注解@DataValidator标注的类
		Map<String, Object> dataValidators = ApplicationContextUtil.getBeansWithAnnotation(DataValidator.class);
		// 绑定验证器
		for (String key : dataValidators.keySet()) {
			// 不重复绑定
			if (validatorsClass.contains(dataValidators.get(key).getClass())) {
				continue;
			}
			binder.addValidators((Validator) dataValidators.get(key));
		}
	}
}
