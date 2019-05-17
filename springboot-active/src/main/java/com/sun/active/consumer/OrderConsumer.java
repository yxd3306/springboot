package com.sun.active.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @JmsListener(destination = "queue1")
    public void receiveTest(String text){
        System.out.println("接收到queue1发送的消息："+text);
    }

}
