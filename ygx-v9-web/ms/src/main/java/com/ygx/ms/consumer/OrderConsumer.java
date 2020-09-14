package com.ygx.ms.consumer;

import com.rabbitmq.client.Channel;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 这个消费者应该写在订单系统内
 */
@Component
public class OrderConsumer {

    @RabbitListener(queues = "order-queue")
    public void process(Map map , Message message , Channel channel){
        System.out.println(map);
        //
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //手工回复确认消息 表示这个消息已处理结束
        //MQ收到确认消息之后，才会发新消息
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
