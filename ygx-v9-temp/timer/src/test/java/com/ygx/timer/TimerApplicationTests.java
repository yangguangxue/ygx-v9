package com.ygx.timer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
class TimerApplicationTests {

    @Test
    void contextLoads() throws IOException {
        System.out.println(new Date());
        System.in.read();
    }

}
