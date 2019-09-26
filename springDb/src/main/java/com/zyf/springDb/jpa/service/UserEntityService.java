package com.zyf.springDb.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyf.springDb.jpa.dao.UserJpaRepository;
import com.zyf.springDb.jpa.entity.UserEntity;

@Service
public class UserEntityService {

	/** 注入操作数据库的接口 */
	@Autowired
	private UserJpaRepository userJpaRepository;

	/** 查询所有用户 */
	public List<UserEntity> findAll() {
		return userJpaRepository.findAll();// findAll方法为jpa自带
	}

	/** 根据主键查询用户 */
	public UserEntity findById(int id) {
		Optional<UserEntity> optional = userJpaRepository.findById(id);// findById方法为jpa自带
		return optional == null ? null : optional.get();
	}

	/** 新建用户 */
	public UserEntity save(UserEntity user) {
		UserEntity res = userJpaRepository.save(user);// save方法为jpa自带
		return res;
	}

	/** 删除用户 */
	public void delete(UserEntity user) {
		// userJpaRepository.delete(user);// delete方法为jpa自带
		userJpaRepository.deleteById(user.getId());// deleteById方法为jpa自带
	}

	/** 根据用户名模糊查询 */
	public List<UserEntity> findByNameLike(UserEntity user) {
		// findByNameLike方法为在UserJpaRepository自定义，需要按照字段规则
		return userJpaRepository.findByNameLike("%" + user.getName() + "%");
	}
	
	/** 根据用户名or备注模糊查询 */
	public List<UserEntity> findByNameLikeOrNoteLike(UserEntity user) {
		// findByNameLikeOrNoteLike方法为在UserJpaRepository自定义，需要按照字段规则
		return userJpaRepository.findByNameLikeOrNoteLike("%" + user.getName() + "%","%" + user.getNote() + "%");
	}
	
	/** 根据用户名and备注模糊查询 */
	public List<UserEntity> findBySexAndNoteLike(UserEntity user) {
		// findByNameLikeOrNoteLike方法为在UserJpaRepository自定义，需要按照字段规则
		return userJpaRepository.findBySexAndNoteLike(user.getSex(),"%" + user.getNote() + "%");
	}
	
	/** 根据用户名and备注模糊查询 */
	public List<UserEntity> findByNameAndNote(UserEntity user) {
		// findByNameLikeOrNoteLike方法为在UserJpaRepository自定义，需要按照字段规则
		return userJpaRepository.findByNameAndNote(user.getName(),user.getNote());
	}
}
