package com.zyf.springwebsocket.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/** 密码工具类 */
public class EncryptUtil {

	private static String SITE_WIDE_SECRET = "uvwxyz";
	private static PasswordEncoder encoder;

	public static String encrypt(String rawPassword) {
		if (null == encoder) {
			setEncoder();
		}
		return encoder.encode(rawPassword);
	}

	public static void setEncoder() {
		encoder = new Pbkdf2PasswordEncoder(SITE_WIDE_SECRET);
	}

	public static PasswordEncoder getEncoder() {
		if (null == encoder) {
			setEncoder();
		}
		return encoder;
	}
}
