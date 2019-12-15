package com.zyf.springmongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zyf.springmongodb.pojo.User;

@Service
public class UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public User insert(User user) {
		return mongoTemplate.save(user, "user");
	}

	public User findById(String id) {
		return mongoTemplate.findById(id, User.class, "user");
	}

	public List<User> findUsers(String name, String note) {
		Criteria criteria = Criteria.where("name").regex(name).and("note").regex(note);
		Query query = Query.query(criteria).limit(100).skip(0);
		return mongoTemplate.find(query, User.class, "user");
	}
}
