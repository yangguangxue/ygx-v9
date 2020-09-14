package com.ygx.ygxv9index;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class YgxV9IndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(YgxV9IndexApplication.class, args);
    }

}
