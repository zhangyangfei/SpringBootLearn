package com.zyf.springMVC.mvcparamvalidate;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zyf.springMVC.common.customannotation.DataValidator;

/** 自定义参数验证器 */
@Component
@Scope
@DataValidator // 标注该类是验证器
public class UserValidator implements Validator {

	/** 声明支持的校验类型为User */
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	/** 校验规则 */
	@Override
	public void validate(Object target, Errors errors) {
		if (null != target) {
			// supports()方法支持User，此处可以强转为User
			User user = (User) target;
			if (new BigDecimal("1000000").compareTo(user.getYue()) > 0) {
				errors.rejectValue("yue", "", "余额不足，请充值！");
			}
		}
	}
}
