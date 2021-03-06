package com.zyf.springTech.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

	@Override
	@Async // 使用异步调用
	public String asynctest(String threadName) {
		System.out.println(threadName + " —> " + Thread.currentThread().getName());
		return null;
	}
}
