package com.zyf.springcloud.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zyf.springcloud.exception.NotFoundException;

// Restcontroller的通知类
@ControllerAdvice(basePackages = "com.zyf.springcloud.user", annotations = { RestController.class })
public class CustomControllerAdvice {

	/**
	 * 拦截异常，异常信息返回调用者
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public Map<String, String> exception(NotFoundException ecp, HttpServletRequest hsr) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", ecp.getCode());
		map.put("message", ecp.getMessage());
		return map;
	}
}
