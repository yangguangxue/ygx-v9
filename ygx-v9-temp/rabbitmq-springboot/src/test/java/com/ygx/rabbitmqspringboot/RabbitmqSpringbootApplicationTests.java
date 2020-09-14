package com.ygx.rabbitmqspringboot;

import com.ygx.rabbitmqspringboot.publish.Sender;
import com.ygx.rabbitmqspringboot.simple.SImpleSender;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqSpringbootApplicationTests {

    @Autowired
    private Sender sender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            System.err.println("ack: " + ack);
            if(!ack){
                System.err.println("异常处理....");
                //消息重发
                //网络原因消息可可能已被处理了名单时生产段没有收到确认
                //重发一遍后 造成消息重复消费
                //换一种问法  怎么保证消息的幂等性

            }
        }
    };

    //回调函数: return返回
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.err.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    @Test
    void contextLoads() {
        sender.send("spring boot rabbit");
    }

    @Test
    public void ttlTest(){
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend("","ttl-queue","hello");
    }
}
