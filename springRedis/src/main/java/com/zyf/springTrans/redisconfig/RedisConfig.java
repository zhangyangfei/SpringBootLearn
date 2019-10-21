package com.zyf.springTrans.redisconfig;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**redis配置类 */
@Configuration
public class RedisConfig {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	/** 修改RedisTemplate的序列化器：用字符串存储key，避免乱码*/
	@PostConstruct // 自定义后初始化方法，在加载完bean到IoC后执行
	public void initRedisSerializer() {
		RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringRedisSerializer);
//		redisTemplate.setValueSerializer(stringRedisSerializer);
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		redisTemplate.setEnableTransactionSupport(true);
	}
}
