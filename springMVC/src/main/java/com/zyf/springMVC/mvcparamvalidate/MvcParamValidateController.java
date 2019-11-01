package com.zyf.springMVC.mvcparamvalidate;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/mvcparamvalidate")
public class MvcParamValidateController {

	@RequestMapping(value = "/mpv1")
	private String mpv1() {
		return "mvcparamvalidate/mpv1";
	}

	/**
	 * JSR-303验证
	 * 	注解@Valid开启验证，否则errors.getAllErrors()不能获取到错误信息
	 */
	@RequestMapping(value = "/mpv2")
	@ResponseBody
	private User mpv2(@Valid @RequestBody User user, Errors errors) {
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
