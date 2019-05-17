package com.sun.active.controller;

import com.sun.active.service.TestProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import java.util.Random;

@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @Autowired
    private TestProducer producer;


    @RequestMapping("/test")
    public String test(){
        Destination destination = new ActiveMQQueue("queue1");
        Random random = new Random(10);
        for (int i=0;i < 100;i++){
            //随机产生优先级数字
            int priority = random.nextInt(9);
            producer.sendMsg(destination,"消息No."+i+"优先级为:"+priority,priority);
        }
        return "发送成功";
    }

}