package com.zyf.springDb.jdbctemplate.service;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.zyf.springDb.jdbctemplate.pojo.User;

import java.sql.ResultSet;

/**
 * User类的映射关系service
 */
@Service
public class UserRowMapperService {
	// 获取查询结果和User类的映射关系
	public RowMapper<User> getRowMapper() {
		RowMapper<User> userRowMapper = (ResultSet rs, int rownum) -> {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getInt("sex"));
			user.setNote(rs.getString("note"));
			return user;
		};
		return userRowMapper;
	}
}
