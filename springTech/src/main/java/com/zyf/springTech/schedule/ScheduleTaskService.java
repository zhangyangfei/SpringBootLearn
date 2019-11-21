package com.zyf.springTech.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 定时任务 */
@Component // ※ 必须装配到IoC
public class ScheduleTaskService {

	// 每5秒执行一次
//	@Scheduled(fixedRate = 5000)
	private void job1() {
		System.out.println("执行job1，时刻=" + System.currentTimeMillis());
	}

	// 每10秒执行一次
//	@Scheduled(fixedRate = 10000)
	@Async // 异步调用（异步线程池）
	private void job2() {
		System.out.println("执行job1，时刻=" + System.currentTimeMillis());
	}

	// cron顺序：秒 分 时 天 月 周 年。年可以省略。通配符*表示任意值，?表示不指定值，避免日期和星期的冲突
	// 每分钟的1秒执行
//	@Scheduled(cron = "1 * * * * ?")
	private void job3() {
		System.out.println("执行job3，时刻=" + System.currentTimeMillis());
	}

	// 每天零点执行
//	@Scheduled(cron = "0 0 0 * * ? ")
	private void job4() {
		System.out.println("执行job4，时刻=" + System.currentTimeMillis());
	}

	// 通配符*表示任意值，?表示不指定值，L表示最后的，-表示时间段
	// 2019年至2023年，每月最后的一个周五，23点30分，0秒，执行
//	@Scheduled(cron = "0 30 23 ? * 6L 2019-2023")
	private void job5() {
		System.out.println("执行job5");
	}
}
