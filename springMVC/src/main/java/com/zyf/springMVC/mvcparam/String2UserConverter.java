package com.zyf.springMVC.mvcparam;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 自定义mvc参数转换器 
 * 	String类型转换为User类型
 */
@Component
public class String2UserConverter implements Converter<String, User> {
	@Override
	public User convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		String[] userArr = source.split("-");
		if (userArr.length == 0) {
			return null;
		}
		User user = new User();
		user.setId(Integer.parseInt(userArr[0]));
		user.setName(userArr[1]);
		user.setSex(Integer.parseInt(userArr[2]));
		user.setNote(userArr[3]);
		return user;
	}
}
