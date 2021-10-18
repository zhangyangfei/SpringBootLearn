package com.zyf.springbootredisson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRedissonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedissonApplication.class, args);
		System.out.println("hello springboot redisson");
	}

}
