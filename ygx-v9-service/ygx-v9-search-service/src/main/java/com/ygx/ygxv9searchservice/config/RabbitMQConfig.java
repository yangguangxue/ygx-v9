package com.ygx.ygxv9searchservice.config;

import com.ygx.v9.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //声明队列
    @Bean
    public Queue getQueue(){
        return new Queue(RabbitMQConstant.BACKGORUND_PRODUCT_SAVE_UPDATE_QUEUE);
    }

    //声明交换机
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitMQConstant.BACKGROUND_EXCHANGE);
    }

    //建立绑定关系
    @Bean
    public Binding getBanding(Queue getQueue,TopicExchange getTopicExchange){
        return BindingBuilder.bind(getQueue).to(getTopicExchange).with("product.add");
    }
}
