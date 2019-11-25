package com.zyf.springcloud.user;

public class User {

	private int id;
	private String name;
	private int sex;
	private String note;

	public User() {
	}

	public User(int id, String name, int sex, String note) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.note = note;
	}

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
