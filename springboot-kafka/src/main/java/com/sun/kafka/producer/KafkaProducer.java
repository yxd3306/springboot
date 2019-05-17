package com.sun.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //这个必须加入容器不然，不会执行
@EnableScheduling //这里是为了测试加入定时调度
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "00/3 * * * * ?")
    public void send(){
        System.out.println("生产消息"+new Date());
        kafkaTemplate.send("topic","kafka data"+new Date());
        //发送方式很多种可以自己研究一下
    }
}
