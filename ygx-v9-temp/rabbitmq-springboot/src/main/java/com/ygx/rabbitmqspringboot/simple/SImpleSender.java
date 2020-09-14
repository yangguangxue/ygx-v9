package com.ygx.rabbitmqspringboot.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SImpleSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg){
        rabbitTemplate.convertAndSend("","spring-simple-queue","springboot rabbit 懂？？");
        System.out.println("发布消息成功！");
    }

}
