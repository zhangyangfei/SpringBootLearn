package com.zyf.springDb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

// mybatis扫描策略：basePackages=基础包，annotationClass = 注解名
@MapperScan(basePackages = "com.zyf.springDb.*.dao", annotationClass = Repository.class)
@SpringBootApplication
public class SpringDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDbApplication.class, args);
		System.out.println("hello springDb");
	}

}
