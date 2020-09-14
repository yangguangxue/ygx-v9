package com.ygx.ygxv9productservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ygx.mapper")
@EnableDubbo
public class YgxV9ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YgxV9ProductServiceApplication.class, args);
    }

}
