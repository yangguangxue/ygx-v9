package com.ygx.rabbitmqspringboot.simple;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleRecv {

    @RabbitListener(queues = "spring-simple-queue")
    public void process(String msg){
        System.out.println("收到消息："+msg);
    }
}
