package com.zyf.springcloud.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.zyf.springcloud.exception.NotFoundException;

@Controller
@ResponseBody // 请求结果默认转换为json
public class UserController {

	// rest请求模板
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping("/user")
	public User insert(@RequestBody User user) {
		// 省略insert过程
		return user;
	}

	@RequestMapping(value = "/user/{id}")
	public User getUser(@PathVariable int id) {
		User user = restTemplate.getForObject("http://localhost:9001" + "/user/{id}", User.class, id);
		return user;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/user/{id}/{name}")
	public List<User> getUsers(@PathVariable String id, @PathVariable String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("name", name);
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:9001" + "/user/{id}/{name}",
				List.class, param);
		List<User> users = responseEntity.getBody();
		if (CollectionUtils.isEmpty(users)) {
			throw new NotFoundException("001", "没有相关用户");
		}
		return users;
	}

	// @RequestMapping("/user/{id}") // 更新全部属性
	// public User update(@PathVariable int id, @RequestBody User user) {
	// // 省略更新过程
	// user.setName("李雷");
	// return user;
	// }

	@RequestMapping("/user/{id}/{name}/{note}") // 更新部分属性
	public User update(@PathVariable int id, @PathVariable String name, @PathVariable String note) {
		// 省略更新过程
		return new User(1, "吉姆格林", 1, "rest风格，更新部分属性");
	}

	// delete、getUser及update的参数完全一样，但是http的动作类型不同，所以能够成功调用
	// @RequestMapping("/user/{id}")
	// public User delete(@PathVariable int id) {
	// // 省略更新过程
	// return new User(1, "吉姆格林", 1, "rest风格，删除");
	// }

}
