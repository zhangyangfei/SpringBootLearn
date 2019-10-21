package com.zyf.springTrans.redismulti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/** redis事务 */
@Service
public class RedisTransService {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	/** 
	 * 事务测试：
	 * 在SessionCallback内执行多个命令只开启一个事务
	 * RedisOperations.watch：监听某个key
	 * RedisOperations.multi：开启事务
	 * RedisOperations.exec：执行命令，exec之前的数据操作只是将操作加入待操作队列
	 */
	public void trans() {
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("key1", "value1");
		redisTemplate.delete("key2");
		Object result = redisTemplate.execute(new SessionCallback<Object>() {
			@Override
			public Object execute(RedisOperations operations) {
				operations.watch("key1");
				operations.multi();
				opsForValue.set("key1", "value1x");// 加入待操作队列
				opsForValue.set("key2", "value2");
				return operations.exec();//在此处打断点，修改key1（被监听）的值，在执行exec(),结果key1，key2的数据都未改变
			}
		});
		System.out.println(result);
		System.out.println("key1 = " + opsForValue.get("key1"));
		System.out.println("key2 = " + opsForValue.get("key2"));
	}
	
	/** 流水线 */
	public void pipeline() {
		//测试存入十万条数据
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		Long start = System.currentTimeMillis();
		List<Object> resultSet = redisTemplate.executePipelined(new SessionCallback<Object>() {
			@Override
			public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
				for(int i = 0 ; i < 100000 ; i ++) {
					opsForValue.set("pipeline"+i, "vpip"+i); //命令存入队列
					if(i == 99999) {
						System.out.println("存入pipeline"+i);
					}
				}
				return null;
			}
		});
		Long end  = System.currentTimeMillis();
		System.out.println("增加数据耗时="+(end - start)+"毫秒");
		
		// 测试删除十万条数据
		Long start2 = System.currentTimeMillis();
		List<Object> resultDel = redisTemplate.executePipelined(new SessionCallback<Object>() {
			@Override
			public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
				for(int i = 0 ; i < 100000 ; i ++) {
					redisTemplate.delete("pipeline"+i);//命令存入队列
					if(i == 99999) {
						System.out.println("删除pipeline"+i);
					}
				}
				return null;
			}
		});
		Long end2  = System.currentTimeMillis();
		System.out.println("删除数据耗时="+(end2 - start2)+"毫秒");
	}
}
