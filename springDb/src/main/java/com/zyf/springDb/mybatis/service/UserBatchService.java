package com.zyf.springDb.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
