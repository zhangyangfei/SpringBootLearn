package com.zyf.springTech.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 定时任务 */
@Component // ※ 必须装配到IoC
public class ScheduleTaskService {

	// 每5秒执行一次
	@Scheduled(fixedRate = 5000)
	public void job1() { // ※ 必须用public修饰
		System.out.println("执行job1，时刻=" + System.currentTimeMillis());
	}

	// 每10秒执行一次
	@Scheduled(fixedRate = 10000)
	@Async // 异步调用（异步线程池）
	public void job2() {
		System.out.println("执行job1，时刻=" + System.currentTimeMillis());
	}

	// spring定时任务的cron顺序：秒 分 时 天 月 周 。
	// ※ 不支持年。
	// 通配符*表示任意值，?表示不指定值，避免日期和星期的冲突
	// 第1列秒（0～59）  
	// 第2列分钟0～59  
	// 第3列小时0～23（0表示子夜）  
	// 第4列日1～31  
	// 第5列月1-12 或者 JAN-DEC  
	// 第6列星期1-7 或者 SUN-SAT（1表示星期天） 
	
	// 每分钟的1秒执行
	@Scheduled(cron = "1 * * * * ?")
	public void job3() {
		System.out.println("执行job3，时刻=" + System.currentTimeMillis());
	}

	// 每天零点执行
	@Scheduled(cron = "0 0 0 * * ? ")
	public void job4() {
		System.out.println("执行job4，时刻=" + System.currentTimeMillis());
	}

	// 周五，23点30分，0秒，执行
	@Scheduled(cron = "0 30 23 ? * 6")
	public void job5() {
		System.out.println("执行job5");
	}
}
