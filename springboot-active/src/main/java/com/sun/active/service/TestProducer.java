package com.sun.active.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.QosSettings;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.Destination;

@Service
public class TestProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(Destination destination, String text, int priority){
        //获取jmsTemplate对象
        JmsTemplate jmsTemplate = jmsMessagingTemplate.getJmsTemplate();
        //创建QosSettings对象
        QosSettings settings = new QosSettings();
        //设置优先级
        settings.setPriority(priority);
        //设置发送模式
        settings.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //设置延时发送时间
        settings.setTimeToLive(1000L);
        //将设置传入
        jmsTemplate.setQosSettings(settings);
        //发送消息
        jmsMessagingTemplate.convertAndSend(destination,text);
    }

}
