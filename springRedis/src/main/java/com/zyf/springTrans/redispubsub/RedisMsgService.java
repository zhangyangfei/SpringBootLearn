package com.zyf.springTrans.redispubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMsgService {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//发布redis消息到制定渠道
	public void sendredismsg() {
		redisTemplate.convertAndSend("topic9", "消息...");
//		stringRedisTemplate.convertAndSend("topic9", "消息...");
	}
}
