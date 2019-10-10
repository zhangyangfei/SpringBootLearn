package com.zyf.springDb;

import java.util.ArrayList;
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
import com.zyf.springDb.mybatis.dto.UserDto;
import com.zyf.springDb.mybatis.service.UserBatchService;
import com.zyf.springDb.mybatis.service.UserMyBatisService;

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
	
//	@Test
	public void findBySexAndNoteLike(){
		UserEntity user = new UserEntity();
		user.setName("zha");
		user.setSex(2);
		user.setNote("好学生");
		List<UserEntity> userList = userEntityService.findBySexAndNoteLike(user);
		System.out.println("用户列表：" + userList);
	}
	
//	@Test
	public void findByNameAndNote(){
		UserEntity user = new UserEntity();
		user.setName("zha");
		user.setSex(2);
		user.setNote("好学生");
		List<UserEntity> userList = userEntityService.findByNameAndNote(user);
		System.out.println("用户列表：" + userList);
	}
	
	@Autowired
	UserMyBatisService userMyBatisService;
//	@Test
	public void getUserById(){
		UserDto userDto = userMyBatisService.getUserById(1);
		System.out.println("用户：" + userDto);
	}
	
	// 传播行为测试  start
	@Autowired
	UserBatchService userBatchService;
	@Test
	public void insertBatch(){
//		int in = userBatchService.insertBatch(getUserList());
		int in = userBatchService.insertBatch2(getUserList());
		System.out.println("批量存入用户：" + in);
	}
	
	private List<UserDto> getUserList(){
		List<UserDto> list = new ArrayList<UserDto>();
		UserDto user = new UserDto();
		user.setId(1);
		user.setName("xues1");
		user.setSex(2);
		user.setNote("好学生1");
		list.add(user);
		
		UserDto user2 = new UserDto();
		user2.setId(1);
		user2.setName("xues2");
		user2.setSex(1);
		user2.setNote("好学生2");
		list.add(user2);
		
		UserDto user3 = new UserDto();
		user3.setId(3);
		user3.setName("xues3");
		user3.setSex(1);
		user3.setNote("好学生3");
		list.add(user3);
		
		return list;
	}
	// 传播行为测试  end
}
