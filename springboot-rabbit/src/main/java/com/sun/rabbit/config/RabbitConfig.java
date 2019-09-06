package com.sun.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue asyncQueue(){
        return new Queue("asyncCreateOrderQueue");
    }
    @Bean
    public Queue asyncResponseQueue(){
        return new Queue("asyncResponseQueue");
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange asyncExchange(){
        return new TopicExchange("asyncExchange");
    }

    /**
     * 队列绑定到交换机
     * @param asyncQueue
     * @param asyncExchange
     * @return
     */
    @Bean
    public Binding bindingOrderExchange(Queue asyncQueue,TopicExchange asyncExchange){
        return BindingBuilder.bind(asyncQueue).to(asyncExchange).with("topic.asyncCreateOrderKey");
    }
    @Bean
    public Binding bindingAsyncResponseExchange(Queue asyncResponseQueue,TopicExchange asyncExchange){
        return BindingBuilder.bind(asyncResponseQueue).to(asyncExchange).with("topic.asyncResponseKey");
    }


}
