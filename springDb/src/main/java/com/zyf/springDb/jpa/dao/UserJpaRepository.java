package com.zyf.springDb.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zyf.springDb.jpa.entity.UserEntity;

/**
 * 操作数据库的接口 extends JpaRepository<实体类, 主键类型>
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

	// 模糊查询，自定义方法，需要按照字段规则命名：findBy+字段名+Like
	public List<UserEntity> findByNameLike(String name);

	// 模糊查询，自定义方法，需要按照字段规则命名：findBy+字段名+Like+Or+字段名+Like
	public List<UserEntity> findByNameLikeOrNoteLike(String name, String note);

	// 模糊查询，自定义方法，需要按照字段规则命名：findBy+字段名+Like+And+字段名+Like
	public List<UserEntity> findBySexAndNoteLike(int sex, String note);
}
