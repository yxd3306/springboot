package com.sun.rabbit.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author 喻湘东
 * @Create 2019-10-08 09:47:30
 */
@Component
@Slf4j
public class AckConsumer {


    @RabbitListener(queues = "ackQueue")
    @RabbitHandler
    public void cousumer(Message message, Channel channel) throws IOException {
        try{
            int i=1/0;
            //Thread.sleep(3000);
            log.info("cousumer1："+new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            //e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }

    }
    @RabbitListener(queues = "ackQueue")
    @RabbitHandler
    public void cousumer2(Message message, Channel channel) throws IOException {
        try{
            //int i=1/0;
            //Thread.sleep(3000);
            log.info("cousumer2："+new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }

    }
    @RabbitListener(queues = "ackQueue")
    @RabbitHandler
    public void cousumer3(Message message, Channel channel) throws IOException {
        try{
            //int i=1/0;
            //Thread.sleep(3000);
            log.info("cousumer3："+new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }

    }

}
