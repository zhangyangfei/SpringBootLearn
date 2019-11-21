package com.zyf.springTech.activemq;

import java.io.Serializable;

public class MessageResponseDto implements Serializable {

	private static final long serialVersionUID = -8500255023579650017L;

	// 业务id
	private String id;

	// 处理结果
	private boolean success;

	// 备注
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
