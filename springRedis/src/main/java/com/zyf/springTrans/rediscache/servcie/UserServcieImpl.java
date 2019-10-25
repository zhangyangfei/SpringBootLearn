package com.zyf.springTrans.rediscache.servcie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zyf.springTrans.rediscache.dao.UserMapper;
import com.zyf.springTrans.rediscache.pojo.User;

/** 缓存注解操作redis */
@Service
public class UserServcieImpl implements UserServcie {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 查询用户 
	 * 优先从redis缓存查，如果查不到，再执行mybatis查询，且将查询结果存入到redis缓存
	 */
	@Override
	@Cacheable(value = "rediscache01", key = "'redis_user_'+#id") // value=缓存名称
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}

	/**
	 * 插入用户 
	 * 存入缓存
	 */
	@Override
	@CachePut(value = "rediscache01", key = "'redis_user_'+#result.id")
	public User insertUser(User user) {
		return user;
	}

	/**
	 * 更新用户 
	 * 存入缓存，存入的前提条件是user不为null
	 */
	@Override
	@CachePut(value = "rediscache01", key = "'redis_user_'+#result.id", condition = "#result != 'null'")
	public User updateUser(User user) {
		return user;
	}

	/**
	 * 删除用户 
	 * 移除缓存，beforeInvocation表示是否在方法前                                                                                                                 移除，默认为false
	 */
	@Override
	@CacheEvict(value = "rediscache01", key = "'redis_user_'+#id", beforeInvocation = false)
	public int deleteUserById(int id) {
		return 0;
	}

	@Override
	public List<User> getUsers(User user) {
		return null;
	}

}
