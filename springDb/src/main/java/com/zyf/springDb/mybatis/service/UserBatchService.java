package com.zyf.springDb.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zyf.springDb.mybatis.dao.UserMapper;
import com.zyf.springDb.mybatis.dto.UserDto;

@Service
public class UserBatchService {

	@Autowired
	private UserMyBatisService userMyBatisService;
	
	// 传播行为
//	@Transactional
//	@Transactional(rollbackFor=Exception.class)
	@Transactional(propagation = Propagation.REQUIRED) // 等同默认：被调用的方法沿用当前事务
//	@Transactional(propagation = Propagation.NESTED) 
	public int insertBatch(List<UserDto> list){
		for (UserDto userDto : list) {
			userMyBatisService.insertUser(userDto);
		}
		return list.size();
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	/**自调用存入用户*/
	@Transactional
	public int insertBatch2(List<UserDto> list){
		UserBatchService userBatchService = applicationContext.getBean(UserBatchService.class);
		for (UserDto userDto : list) {
			this.insertUser(userDto);//自调用：一个类自身方法的调用
//			userBatchService.insertUser(userDto);//非自调用：从IoC中取一次UserBatchService的bean来调用，解决@Transactional自调用失效的问题。
		}
		return list.size();
	}
	
	@Autowired
	private UserMapper userMapper;
	
	/**存入用户*/
	@Transactional(propagation = Propagation.REQUIRES_NEW) // 自调用会导致@Transactional失效
	public int insertUser(UserDto user){
		return userMapper.insertUser(user);
	}
}
