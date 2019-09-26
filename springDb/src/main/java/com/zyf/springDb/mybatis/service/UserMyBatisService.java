package com.zyf.springDb.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyf.springDb.mybatis.dao.UserMapper;
import com.zyf.springDb.mybatis.dto.UserDto;

@Service
public class UserMyBatisService {

	@Autowired
	private UserMapper userMapper;
	
	public UserDto getUserById(int id){
		return userMapper.getUserById(id);
	}
}
