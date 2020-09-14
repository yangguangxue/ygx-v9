package com.ygx.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String  QUEUE_NAME = "work_queeu";

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
        //声明队列 若队列不存在  则会创建 否则不做处理
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        for (int i = 0; i < 10; i++) {
            String msg = "第"+i+"次";
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }
        System.out.println("消息发送完毕");

    }

}
