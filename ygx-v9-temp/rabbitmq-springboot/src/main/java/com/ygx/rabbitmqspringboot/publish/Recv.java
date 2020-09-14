package com.ygx.rabbitmqspringboot.publish;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Recv {

    @RabbitListener(queues = "springboot-faout-queue-1")
    public void process(String msg){
        System.out.println("1111收到消息："+msg);
    }

    @RabbitListener(queues = "springboot-faout-queue-2")
    public void process2(String msg){
        System.out.println("222收到消息："+msg);
    }
}
