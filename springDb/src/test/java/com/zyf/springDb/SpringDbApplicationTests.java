package com.zyf.springDb;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.zyf.springDb.jdbctemplate.pojo.User;
import com.zyf.springDb.jdbctemplate.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDbApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// @Test
	public void contextLoads() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_user");
		System.out.println(list);
	}

	@Autowired
	private UserService userService;

//	@Test
	public void queryUserById() {
		User user = userService.getUser(1);
		System.out.println(user.toString());
	}

//	@Test
	public void insertUser() {
		User user = new User();
		user.setName("lisi");
		user.setSex(2);
		user.setNote("lisi是个好学生");
		int count = userService.insertUser(user);
		System.out.println("存入用户条数：" + count);
	}

//	@Test
	public void updateUser() {
		int count = userService.deleteUser(2);
		System.out.println("删除用户条数：" + count);
	}
	
	@Test
	public void getUsers() {
		User user = new User();
		user.setName("li");
		List<User> users = userService.getUsers(user);
		System.out.println("查询用户数量：" + users.size());
	}
}
