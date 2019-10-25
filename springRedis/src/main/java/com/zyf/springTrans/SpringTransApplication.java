package com.zyf.springTrans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.zyf.**.dao", annotationClass = Repository.class)
public class SpringTransApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTransApplication.class, args);
		System.out.println("hello SpringTrans");
	}

}
