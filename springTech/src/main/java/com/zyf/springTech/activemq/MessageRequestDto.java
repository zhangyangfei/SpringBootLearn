package com.zyf.springTech.activemq;

import java.io.Serializable;

public class MessageRequestDto implements Serializable {

	private static final long serialVersionUID = -8532307584713967506L;

	// 业务id
	private String id;

	// 业务数据
	private String data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
