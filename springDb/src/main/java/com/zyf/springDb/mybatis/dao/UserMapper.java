package com.zyf.springDb.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zyf.springDb.mybatis.dto.UserDto;

@Repository // 扫描到IoC
@Mapper
public interface UserMapper {
	UserDto getUserById(int id);
	int insertUser(UserDto userDto);
}
