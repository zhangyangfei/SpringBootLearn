package com.zyf.springDb.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 与表t_user对应的实体类
 */
@Entity // 实体类
@Table(name = "t_user") // 对应的表名
public class UserEntity {

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键策略，递增
	private int id;

	@Column(name = "name") // 属性和表字段的映射
	private String name;

	@Column(name = "sex")
	private int sex;

	@Column(name = "note")
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
