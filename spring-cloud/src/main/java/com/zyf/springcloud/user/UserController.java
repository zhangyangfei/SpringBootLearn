package com.zyf.springcloud.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * rest风格站点
 */
@RestController // 请求结果默认转换为json
public class UserController {

	@PostMapping("/user")
	public User insert(@RequestBody User user) {
		// 省略insert过程
		return user;
	}

	// consumes限定请求类型。produces限定返回类型。
	@GetMapping(value = "/user/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User getUser(@PathVariable int id) {
		// 省略主键查询过程
		return new User(1, "李四", 1, "rest风格获取用户");
	}

	@GetMapping("/user/{id}/{name}")
	public List<User> getUsers(@PathVariable int id, @PathVariable String name) {
		// 省略条件查询过程
		User[] users = { new User(1, "张三", 1, "rest风格获取用户集合"), new User(2, "李四", 1, "rest风格获取用户") };
		return Arrays.asList(users);
	}

	@PutMapping("/user/{id}") // 更新全部属性
	public User update(@PathVariable int id, @RequestBody User user) {
		// 省略更新过程
		user.setName("李雷");
		return user;
	}

	@PatchMapping("/user/{id}/{name}/{note}") // 更新部分属性
	public User update(@PathVariable int id, @PathVariable String name, @PathVariable String note) {
		// 省略更新过程
		return new User(1, "吉姆格林", 1, "rest风格，更新部分属性");
	}

	// delete、getUser及update的参数完全一样，但是http的动作类型不同，所以能够成功调用
	@DeleteMapping("/user/{id}")
	public User delete(@PathVariable int id) {
		// 省略更新过程
		return new User(1, "吉姆格林", 1, "rest风格，删除");
	}
}
