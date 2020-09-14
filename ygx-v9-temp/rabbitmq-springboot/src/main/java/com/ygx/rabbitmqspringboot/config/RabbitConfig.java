package com.ygx.rabbitmqspringboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue getQueue(){
        return new Queue("spring-simple-queue",true,false,false);
    }

    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("springboot-fanout-exchange");
    }

    @Bean
    public Queue getQueueOne(){
        return new Queue("springboot-faout-queue-1");
    }

    @Bean
    public Queue getQueueTwo(){
        return new Queue("springboot-faout-queue-2");
    }

    //绑定
    @Bean
    public Binding getBindingOne(FanoutExchange getFanoutExchange, Queue getQueueOne){
        return BindingBuilder.bind(getQueueOne).to(getFanoutExchange);
    }

    //绑定
    @Bean
    public Binding getBindingTwo(FanoutExchange getFanoutExchange, Queue getQueueTwo){
        return BindingBuilder.bind(getQueueTwo).to(getFanoutExchange);
    }
}
