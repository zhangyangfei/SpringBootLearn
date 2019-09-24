package com.zyf.springDb.jpa.dao;

import java.util.List;

import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zyf.springDb.jpa.entity.UserEntity;

/**
 * 操作数据库的接口 extends JpaRepository<实体类, 主键类型>
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

	public List<UserEntity> findByNameLike(String name);
	
	public List<UserEntity> findByNameLikeOrNoteLike(String name,String note);
	
	public List<UserEntity> findBySexAndNoteLike(int sex,String note);
}
