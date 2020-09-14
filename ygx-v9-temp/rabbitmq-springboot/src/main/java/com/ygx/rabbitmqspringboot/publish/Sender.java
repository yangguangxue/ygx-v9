package com.ygx.rabbitmqspringboot.publish;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.nio.cs.ext.MS874;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg){
        rabbitTemplate.convertAndSend("springboot-fanout-exchange","", msg);
        System.out.println("发布消息成功！");
    }

}
