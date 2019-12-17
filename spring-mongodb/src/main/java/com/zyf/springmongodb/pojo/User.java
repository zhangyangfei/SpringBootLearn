package com.zyf.springmongodb.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

// 文档实体类
@Document //该pojo将作为MongoDB文档存在
public class User implements Serializable{//必须实现序列化接口
	
	private static final long serialVersionUID = 7767440606893451627L;

	@Id //文档主键
	private String id;
	
	@Field("name")//关联数据库中的字段，二者不同时标注，相同则不用标注
	private String name;

	private int age;

	private String note;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
