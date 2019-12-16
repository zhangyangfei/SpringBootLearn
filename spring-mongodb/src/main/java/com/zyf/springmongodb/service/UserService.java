package com.zyf.springmongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zyf.springmongodb.pojo.User;

@Service
public class UserService {

	// 操作mongodb的模板
	@Autowired
	private MongoTemplate mongoTemplate;

	// 数据库集合名称
	private String collectionName = "user";

	// 保存或更新
	public User insert(User user) {
		// 保存，主键已有则更新其所有属性
		return mongoTemplate.save(user, collectionName);
	}

	// 根据主键查询
	public User findById(String id) {
		return mongoTemplate.findById(id, User.class, collectionName);
	}

	// 条件查询，or
	public List<User> findUsers(String name, String note) {
		Criteria criteriaName = Criteria.where("name").regex(name);
		Criteria criteriaNote = Criteria.where("note").regex(note);
		Criteria criteria = new Criteria();
		criteria.orOperator(criteriaName, criteriaNote);// 或者
		Query query = Query.query(criteria).limit(100).skip(0);
		return mongoTemplate.find(query, User.class, collectionName);
	}

	// 条件查询，and
	public List<User> findUsers2(String name, int age) {
		Criteria criteria = Criteria.where("name").regex(name).and("age").is(age); // 并且
		Query query = Query.query(criteria).limit(100).skip(0);
		return mongoTemplate.find(query, User.class, collectionName);
	}

	// 根据主键更新，如果所有属性都没有变化则getModifiedCount返回0
	public long updateById(String id, String note, int age) {
		Criteria criteria = Criteria.where("id").is(id);
		Update update = Update.update("note", note);
		update.set("age", age);
		Query query = Query.query(criteria);
		UpdateResult updateResult = mongoTemplate.updateMulti(query, update, User.class, collectionName);
		return updateResult.getModifiedCount();
	}

	// 根据主键删除
	public long deleteById(String id) {
		Criteria criteria = Criteria.where("id").is(id);
		Query query = Query.query(criteria);
		DeleteResult deleteResult = mongoTemplate.remove(query, User.class, collectionName);
		return deleteResult.getDeletedCount();
	}
}
