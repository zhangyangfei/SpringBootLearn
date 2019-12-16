package com.zyf.springmongodb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return userService.findUsers(name, note);
	}

	@RequestMapping("/findUsers2")
	@ResponseBody
	public List<User> findUsers2(String name, int age) {
		return userService.findUsers2(name, age);
	}

	@RequestMapping("/updateById")
	@ResponseBody
	public Map<String, Object> updateById(String id, String note, int age) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		long u = userService.updateById(id, note, age);
		resultMap.put("message", "成功更新数据条数：" + u);
		return resultMap;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String, Object> deleteById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		long d = userService.deleteById(id);
		resultMap.put("message", "成功删除数据条数：" + d);
		return resultMap;
	}
}
