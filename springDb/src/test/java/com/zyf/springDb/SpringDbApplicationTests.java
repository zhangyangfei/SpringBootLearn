package com.zyf.springDb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zyf.springDb.jdbctemplate.pojo.User;
import com.zyf.springDb.jdbctemplate.service.UserService;
import com.zyf.springDb.jpa.entity.UserEntity;
import com.zyf.springDb.jpa.service.UserEntityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDbApplicationTests {

	// @Test
	public void contextLoads() {
		System.out.println(userService.getUserList());
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
	
//	@Test
	public void getUsers() {
		User user = new User();
		user.setName("li");
		List<User> users = userService.getUsers(user);
		System.out.println("查询用户数量：" + users.size());
	}
	
//	@Test
	public void getUserList2(){
		User user = new User();
		user.setName("lisi");
		user.setSex(2);
		user.setNote("lisi是个好学生");
		
		System.out.println("用户列表：" + userService.getUserList2(user));
	}
	
	@Autowired
	private UserEntityService userEntityService;
//	@Test
	public void findAll(){
		List<UserEntity> userList = userEntityService.findAll();
		System.out.println("用户列表：" + userList);
	}
	
//	@Test
	public void findById(){
		userEntityService.findById(1);
		System.out.println("用户列表：" );
	}
	
//	@Test
	public void save(){
		UserEntity user = new UserEntity();
		user.setName("zhaoliu");
		user.setSex(2);
		user.setNote("zhaoliu是个好学生");
		userEntityService.save(user);
		System.out.println("用户：" + user );
	}
	
//	@Test
	public void delete(){
		UserEntity user = new UserEntity();
		user.setName("zhaoliu");
		user.setSex(2);
		user.setNote("zhaoliu是个好学生");
		user.setId(11);
		userEntityService.delete(user);
		System.out.println("用户：" + user );
	}
	
//	@Test
	public void findByNameLike(){
		UserEntity user = new UserEntity();
		user.setName("zha");
		List<UserEntity> userList = userEntityService.findByNameLike(user);
		System.out.println("用户列表：" + userList);
	}
	
//	@Test
	public void findByNameLikeOrNoteLike(){
		UserEntity user = new UserEntity();
		user.setName("zha");
		user.setNote("好学生");
		List<UserEntity> userList = userEntityService.findByNameLikeOrNoteLike(user);
		System.out.println("用户列表：" + userList);
	}
	
	@Test
	public void findBySexAndNoteLike(){
		UserEntity user = new UserEntity();
		user.setName("zha");
		user.setSex(2);
		user.setNote("好学生");
		List<UserEntity> userList = userEntityService.findBySexAndNoteLike(user);
		System.out.println("用户列表：" + userList);
	}
}
