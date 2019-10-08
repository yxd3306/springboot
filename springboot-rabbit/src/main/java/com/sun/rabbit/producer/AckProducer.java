package com.sun.rabbit.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @Author 喻湘东
 * @Create 2019-10-08 09:47:13
 */
@Component
public class AckProducer {

    private static final String EXCHANGE = "asyncExchange";
    private static final String ROUTING_KEY = "topic.ackQueueKey";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg, correlationData);
    }



}
