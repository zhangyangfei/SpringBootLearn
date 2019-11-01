package com.zyf.springMVC.mvcparamvalidate;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**用户，增加JSR-303验证*/
public class User {

	@NotNull(message = "不能为空")
	private String id;

	@Size(min = 3, max = 10, message = "长度必须在3到10")
	private String name;

	@Max(value = 3, message = "最大值为3")
	@Min(value = 1, message = "最小值为1")
	private int sex;

	@Size(min = 0, max = 100, message = "长度必须在0到100")
	private String note;

	@Past(message = "只能是过去的日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@Future(message = "只能是将来的日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fut;

	@DecimalMax(value = "1000.00", message = "不能大于1000.00")
	@DecimalMin(value = "0.01", message = "不能小于0.01")
	private BigDecimal yue;

	@Email(message = "必须符合邮箱地址格式")
	private String email;

	@Range(min = 1, max = 20, message = "范围必须是1到20")
	private Long rang;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getFut() {
		return fut;
	}

	public void setFut(Date fut) {
		this.fut = fut;
	}

	public BigDecimal getYue() {
		return yue;
	}

	public void setYue(BigDecimal yue) {
		this.yue = yue;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRang() {
		return rang;
	}

	public void setRang(Long rang) {
		this.rang = rang;
	}
}
