package com.zyf.springTrans.redislua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/** redis lua脚本 */
@Service
public class RedisLuaDemo {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	/** redis lua脚本入门 */
	public void testLua() {
		DefaultRedisScript<String> drs = new DefaultRedisScript<String>();
		// 设置脚本
		drs.setScriptText("return '你好 redis lua'");
		// 设置脚本执行返回类型
		drs.setResultType(String.class);
		// 序列化器
		RedisSerializer<String> rs = redisTemplate.getStringSerializer();
		// 执行lua
		String res = redisTemplate.execute(drs, rs, rs, null);
		System.out.println("输出lua脚本结果：" + res);
	}

}
