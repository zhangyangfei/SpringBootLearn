package com.zyf.springTrans.rediscache.pojo;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1682049189405672046L;
	private int id;
	private String name;
	private int sex;
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "id=" + id + ",name=" + name + ",sex=" + sex + ",note=" + note;
	}
}
