package com.zyf.springTrans.redispubsub;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/** Redis任务池*/
@Component
public class RedisTaskPool {

	/**任务池，运行线程等待处理Redis消息*/
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	@Bean(name = "threadPoolTaskScheduler")
	public ThreadPoolTaskScheduler initThreadPoolTaskScheduler() {
		if (null != threadPoolTaskScheduler) {
			return threadPoolTaskScheduler;
		}
		threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(20);
		return threadPoolTaskScheduler;
	}

}
