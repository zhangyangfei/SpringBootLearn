package com.zyf.springMVC.mvcparamvalidate;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/mvcparamvalidate")
public class MvcParamValidateController {

	@RequestMapping(value = "/mpv1")
	public String mpv1() {
		return "mvcparamvalidate/mpv1";
	}

	/**
	 * JSR-303参数验证 注解@Valid开启验证，否则errors.getAllErrors()不能获取到错误信息
	 */
	@RequestMapping(value = "/mpv2")
	@ResponseBody
	public User mpv2(@Valid @RequestBody User user, Errors errors) {
		List<ObjectError> errList = errors.getAllErrors();
		for (ObjectError oError : errList) {
			// 如果是字段错误
			if (oError instanceof FieldError) {
				FieldError fe = (FieldError) oError;
				System.out.println(fe.getField() + "=" + fe.getRejectedValue() + "，消息=" + fe.getDefaultMessage());
			} else {
				System.out.println(oError.getObjectName() + "，消息=" + oError.getDefaultMessage());
			}
		}
		return user;
	}

	/**
	 * 绑定自定义参数校验器 执行控制器方法前会先执行@InitBinder方法 用setValidator()方法，原来的JSR-303则不做校验了
	 * 用addValidators()方法，原来的JSR-303校验依旧有效，且自定义校验也有效（推荐）
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.setValidator(new UserValidator());//设定参数校验器(先清空原有校验器，再设定)
		// binder.addValidators(new UserValidator());// 增加参数校验器（可多个）
		// ---> 到共通CommonControllerAdvice绑定验证器
	}

	/**
	 * 自定义参数校验 注解@Valid开启验证，否则JSR-303验证和UserValidator()都不生效
	 */
	@RequestMapping(value = "/mpv3")
	@ResponseBody
	public User mpv3(@Valid @RequestBody User user, Errors errors) {
		List<ObjectError> errList = errors.getAllErrors();
		for (ObjectError oError : errList) {
			// 如果是字段错误
			if (oError instanceof FieldError) {
				FieldError fe = (FieldError) oError;
				System.out.println(fe.getField() + "=" + fe.getRejectedValue() + "，消息=" + fe.getDefaultMessage());
			} else {
				System.out.println(oError.getObjectName() + "，消息=" + oError.getDefaultMessage());
			}
		}
		return user;
	}
}
