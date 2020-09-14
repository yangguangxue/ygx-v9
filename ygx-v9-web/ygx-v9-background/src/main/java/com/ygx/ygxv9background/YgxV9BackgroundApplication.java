package com.ygx.ygxv9background;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDubbo
@Import(FdfsClientConfig.class)
public class YgxV9BackgroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(YgxV9BackgroundApplication.class, args);
    }

}
