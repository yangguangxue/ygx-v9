package com.ygx.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.ygx.ms.dao")
@EnableScheduling //开启定时任务
public class MsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApplication.class, args);
    }

}
