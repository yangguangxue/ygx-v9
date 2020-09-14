package com.ygx.ygxitemservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.ygx.mapper")
public class YgxItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YgxItemServiceApplication.class, args);
    }

}
