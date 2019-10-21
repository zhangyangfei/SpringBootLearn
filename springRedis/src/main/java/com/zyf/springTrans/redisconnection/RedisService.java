package com.zyf.springTrans.redisconnection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	public void redisOperate() {
		redisTemplate.opsForValue().set("key1", "值value1");
		Object value1 = redisTemplate.opsForValue().get("key1");
		System.out.println("key1在redis中对应的值=" + value1);
		Set<Object> sts = redisTemplate.keys("*");
		System.out.println(sts);
		// 删除，根据key
		redisTemplate.delete("key1");
		// 删除，根据key组成的集合
		redisTemplate.delete(sts);
	}

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public void stringRedisOperate() {
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		stringRedisTemplate.opsForValue().set("key11", "value11");
		Object value11 = stringRedisTemplate.opsForValue().get("key11");
		System.out.println("key11在redis中对应的值=" + value11);
		Set<String> sts = stringRedisTemplate.keys("*");
		System.out.println(sts);
		
		opsForValue.set("int1", "1");
		System.out.println("1+999 = " + (opsForValue.increment("int1", 999)));
	}

	/** 7种数据类型的操作接口 */
	@SuppressWarnings("unused")
	public void operateType() {
		// 1.地理位置
		GeoOperations<Object, Object> opsForGeo = redisTemplate.opsForGeo();
		// 2.散列
		HashOperations<Object, Object, Object> opsForHash = redisTemplate.opsForHash();
		// 3.基数
		HyperLogLogOperations<Object, Object> opsForHyperLogLog = redisTemplate.opsForHyperLogLog();
		// 4.列表
		ListOperations<Object, Object> opsForList = redisTemplate.opsForList();
		// 5.集合
		SetOperations<Object, Object> opsForSet = redisTemplate.opsForSet();
		// 6.字符串
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		// 7.有序集合
		ZSetOperations<Object, Object> opsForZSet = redisTemplate.opsForZSet();
	}

	/**绑定键的操作类，传入key可以获取到对应的数据操作对象*/
	@SuppressWarnings("unused")
	public void boundType() {
		// 1.地理位置
		BoundGeoOperations<Object, Object> boundGeoOps = redisTemplate.boundGeoOps("key");
		// 2.散列
		BoundHashOperations<Object, Object, Object> boundHashOps = redisTemplate.boundHashOps("key");
		// 3.列表
		BoundListOperations<Object, Object> boundListOps = redisTemplate.boundListOps("key");
		// 4.集合
		BoundSetOperations<Object, Object> boundSetOps = redisTemplate.boundSetOps("key");
		// 5.字符串
		BoundValueOperations<Object, Object> boundValueOps = redisTemplate.boundValueOps("key");
		// 6.有序集合
		BoundZSetOperations<Object, Object> boundZSetOps= redisTemplate.boundZSetOps("key");
	}
	
	/**字符串操作*/
	public void operateString() {
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("str1", "增加一个字符串");
		opsForValue.set("int1", 1);
		BoundValueOperations<Object, Object> boundValueOps = redisTemplate.boundValueOps("int1");
		System.out.println((int) boundValueOps.get() + 2);
	}
	
	/**散列操作*/
	public void operateHash() {
		// 散列操作接口
		HashOperations<Object, Object, Object> opsForHash = redisTemplate.opsForHash();
		Map<Object, Object> map = new HashMap<>();
		map.put("name", "zhangsan");
		map.put("age", 18);
		// 将map放入到HashOperations中
		opsForHash.putAll("hash2", map);
		// 获取指定map的指定key的数据：opsForHash.get(代表map的key, map中的key);
		Object age = opsForHash.get("hash2", "age");
		System.out.println("age = " + age);
		//给指定map中增加或修改某key对应的数据
		opsForHash.put("hash2", "sex", "female");
		Object sex  = opsForHash.get("hash2", "sex");
		System.out.println("sex = " + sex);
		// 将map绑定到散列绑定键操作接口
		BoundHashOperations<Object, Object, Object> hash2 = redisTemplate.boundHashOps("hash2");
		hash2.put("age", 25);
		System.out.println("age = " + hash2.get("age"));
	}
	
	/**链表操作1*/
	public void operateList1() {
		ListOperations<Object, Object> opsForList = redisTemplate.opsForList();
		// 从左边存入数据，存入后顺序为：v5,v4,v3,v2,v1
		opsForList.leftPushAll("list1", "v1","v2","v3","v4","v5");
		BoundListOperations<Object, Object> boundListOps = redisTemplate.boundListOps("list1");
		System.out.println("list1中元素个数 = " + boundListOps.size());
		
		Long listSize = boundListOps.size();
		for(int i = 0; i < listSize;i++) {
			//弹出左边的数据（移除）
			Object left = boundListOps.leftPop();
			System.out.println("弹出左边的数据:" + left);
		}
	}
	
	/**链表操作2*/
	public void operateList2() {
		ListOperations<Object, Object> opsForList = redisTemplate.opsForList();
		opsForList.leftPushAll("list1", "v1","v2","v3","v4","v5","v2");
		BoundListOperations<Object, Object> boundListOps = redisTemplate.boundListOps("list1");
		
		// boundListOps.remove(移除个数, 移除的值)，返回书记移除的个数
		Long lon = boundListOps.remove(3, "v2");// 移除3个值为“v2”的数据
		System.out.println("移除v2的个数="+lon);
		
		Long listSize = boundListOps.size();
		for(int i = 0; i < listSize;i++) {
			//弹出右边的数据
			boundListOps.leftPop();
		}
	}
}
