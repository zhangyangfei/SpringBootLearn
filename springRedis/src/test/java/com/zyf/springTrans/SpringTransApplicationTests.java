package com.zyf.springTrans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zyf.springTrans.rediscache.pojo.User;
import com.zyf.springTrans.rediscache.servcie.UserServcie;
import com.zyf.springTrans.redisconnection.RedisService;
import com.zyf.springTrans.redislua.RedisLuaDemo;
import com.zyf.springTrans.redismulti.RedisTransService;
import com.zyf.springTrans.redispubsub.RedisMsgService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTransApplicationTests {

//	@Test
	public void contextLoads() {
		System.out.println("hello springTrans test");
	}
	
	@Autowired
	RedisService redisService;
//	@Test
	public void redisOperate() {
		redisService.redisOperate();
	}

//	@Test
	public void stringRedisOperate() {
		redisService.stringRedisOperate();
	}
	
//	@Test
	public void operateType() {
		redisService.operateType();
	}
	
//	@Test
	public void operateString() {
		redisService.operateString();
	}
	
//	@Test
	public void operateHash() {
		redisService.operateHash();
	}
	
//	@Test
	public void operateList() {
		redisService.operateList1();
	}
	
//	@Test
	public void operateList2() {
		redisService.operateList2();
	}
	
	@Autowired
	private RedisTransService redisTransService;
//	@Test
	public void trans() {
		redisTransService.trans();
	}
	
//	@Test
	public void pipeline() {
		redisTransService.pipeline();
	}
	
	@Autowired
	RedisMsgService redisMsgService;
//	@Test
	public void sendredismsg() {
		redisMsgService.sendredismsg();
	}
	
	@Autowired
	RedisLuaDemo redisLuaDemo;
//	@Test
	public void testLua() {
		redisLuaDemo.testLua();
	}
	
	@Autowired
	UserServcie userServcie;
	
//	@Test
	public void getUserById() {
		System.out.println("=================== 测试 userServcie.getUserById() 开始=======================");
		Long start = System.currentTimeMillis();
		System.out.println("查询用户:"+userServcie.getUserById(14));
		Long end = System.currentTimeMillis();
		System.out.println("耗时="+(end - start));
		System.out.println("=================== 测试 userServcie.getUserById() 结束=======================");
	}
	
//	@Test
	public void insertUser() {
		User user = new User();
		user.setName("redis1");
		user.setNote("redis缓存测试。。。");
		user.setSex(3);
		System.out.println("=================== 测试 userServcie.insertUser() 开始=======================");
		System.out.println("存入用户:"+userServcie.insertUser(user));
		System.out.println("=================== 测试 userServcie.insertUser() 结束=======================");
	}
	
//	@Test
	public void updateUser() {
		User user = new User();
		user.setId(14);
		user.setName("redis14");
		user.setNote("更新缓存14...");
		user.setSex(14);
		System.out.println("=================== 测试 userServcie.updateUser() 开始=======================");
		System.out.println("更新用户:"+userServcie.updateUser(user));
		System.out.println("=================== 测试 userServcie.updateUser() 结束=======================");
	}
	
//	@Test
	public void deleteUserById() {
		System.out.println("=================== 测试 userServcie.deleteUserById() 开始=======================");
		System.out.println("删除用户:"+userServcie.deleteUserById(14));
		System.out.println("=================== 测试 userServcie.deleteUserById() 结束=======================");
	}
}
