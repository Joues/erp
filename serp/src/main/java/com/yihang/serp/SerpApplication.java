package com.yihang.serp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yihang.serp.mapper")
public class SerpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerpApplication.class, args);
    }

}
