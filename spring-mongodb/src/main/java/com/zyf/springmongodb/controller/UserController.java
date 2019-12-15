package com.zyf.springmongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyf.springmongodb.pojo.User;
import com.zyf.springmongodb.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// http://localhost:8080/user/user
	@RequestMapping("/user")
	public String user() {
		return "user/user";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public User insert(@RequestBody User user) {
		return userService.insert(user);
	}

	@RequestMapping("/findById")
	@ResponseBody
	public User findById(String id) {
		return userService.findById(id);
	}
	
	@RequestMapping("/findUsers")
	@ResponseBody
	public List<User> findUsers(String name, String note) {
		return userService.findUsers(name,note);
	}
}
