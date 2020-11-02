package com.zyf.mpgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MpGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MpGeneratorApplication.class, args);
        System.out.println("Mp-Generator启动完成！");
    }
}
