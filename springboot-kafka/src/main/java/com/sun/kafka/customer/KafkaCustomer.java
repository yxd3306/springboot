package com.sun.kafka.customer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component //同样这里是必须的
public class KafkaCustomer {

    @KafkaListener(topics = {"topic"})
    public void receive(String message){
        System.out.println("消费消息"+message);
    }
}
