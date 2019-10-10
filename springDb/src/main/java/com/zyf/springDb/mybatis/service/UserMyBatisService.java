package com.zyf.springDb.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyf.springDb.mybatis.dao.UserMapper;
import com.zyf.springDb.mybatis.dto.UserDto;

@Service
public class UserMyBatisService {

	@Autowired
	private UserMapper userMapper;

	// 隔离级别
//	@Transactional(isolation = Isolation.DEFAULT) // 默认
//	@Transactional(isolation = Isolation.READ_COMMITTED) // 读写提交
//	@Transactional(isolation = Isolation.READ_UNCOMMITTED) // 未读提价
//	@Transactional(isolation = Isolation.REPEATABLE_READ) // 可重复读
	@Transactional(isolation = Isolation.SERIALIZABLE) // 串行化
	public UserDto getUserById(int id) {
		return userMapper.getUserById(id);
	}
	
	// 传播行为
	/**存入用户*/
//	@Transactional // 默认：被调用的方法（userProService.insertUser）沿用当前事务
//	@Transactional(propagation = Propagation.REQUIRED) // 等同默认：被调用的方法沿用当前事务
	@Transactional(propagation = Propagation.REQUIRES_NEW) // 被调用的方法开启新的事务
//	@Transactional(propagation = Propagation.NESTED) // 被调用的方法开启新的事务
	public int insertUser(UserDto user){
		return userMapper.insertUser(user);
	}
}
