package com.sun.rabbit.producer;

import com.sun.rabbit.entity.MessageContainer;
import com.sun.rabbit.entity.UserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 09:32:35
 */
//@Component
@Slf4j
public class AsyncCreateOrderSend {

    private static final String EXCHANGE = "asyncExchange";
    private static final String ROUTING_KEY = "topic.asyncCreateOrderKey";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(UserMessage userMessage){
        this.rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,userMessage);
        // log.info("AsyncCreateOrderSend消息发送成功：{}",userMessage);
    }

}
