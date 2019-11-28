package com.zyf.springcloud.exception;

/**
 * 找不到数据异常
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5029748381343053538L;

	// 异常码
	private String code;

	// 异常信息
	private String message;

	public NotFoundException() {
	}

	public NotFoundException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
