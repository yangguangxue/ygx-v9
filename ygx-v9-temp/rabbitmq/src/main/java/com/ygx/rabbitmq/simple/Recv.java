package com.ygx.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    private static final String  QUEUE_NAME = "simple_queeu";

    public static void main(String[] args) throws IOException, TimeoutException {
        //连接MQ服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("/ygx");
        factory.setUsername("ygx");
        factory.setPassword("123");
        factory.setHost("192.168.13.129");
        factory.setPort(5672);
        //发送消息给MQ服务器
        Connection connection = factory.newConnection();
        //基于channel , 类似会话作用
        Channel channel = connection.createChannel();
        //声明消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("收到的消息为："+msg);
            }
        };
        //让消费者去监听队列
        //autoAck:true为自动回复
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
