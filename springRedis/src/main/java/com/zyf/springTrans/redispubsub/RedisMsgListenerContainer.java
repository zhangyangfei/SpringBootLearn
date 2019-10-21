package com.zyf.springTrans.redispubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**Redis监听容器，容器内可以绑定多个监听器*/
@Component
public class RedisMsgListenerContainer {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	@Autowired
	private RedisMessageListener redisMessageListener;
	@Autowired
	private RedisMessageListener2 redisMessageListener2;

	@Bean(name="redisMessageListenerContainer")
	public RedisMessageListenerContainer initRedisMessageListenerContainer() {
		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
		redisMessageListenerContainer.setTaskExecutor(threadPoolTaskScheduler);
		// 渠道
		Topic topic = new ChannelTopic("topic9");
		//可以添加若干监听终端来监听渠道
		redisMessageListenerContainer.addMessageListener(redisMessageListener, topic);
		redisMessageListenerContainer.addMessageListener(redisMessageListener2, topic);
		
		return redisMessageListenerContainer;
	}
}
