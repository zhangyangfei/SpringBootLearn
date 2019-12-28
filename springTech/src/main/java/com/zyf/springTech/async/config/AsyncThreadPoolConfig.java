package com.zyf.springTech.async.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/** 异步线程池配置类 */
@Configuration
@EnableAsync // 开启spring异步
public class AsyncThreadPoolConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		// 定义线程池【spring提供的线程池配置类，对ThreadPoolExecutor进行配置】
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		// 核心线程数=启用线程的个数
		threadPoolTaskExecutor.setCorePoolSize(10);
		// 线程池最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(300);
		// 线程队列最大线程数
		threadPoolTaskExecutor.setQueueCapacity(2000);
		// 初始化线程池
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		System.out.println("异常了......");
		return null;
	}
}
