package com.zyf.springTrans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zyf.springTrans.redisconnection.RedisService;
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
	@Test
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
}
