package com.zyf.springTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.zyf")
public class SpringTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTechApplication.class, args);
		System.out.println("hello springTech...");
	}

}
