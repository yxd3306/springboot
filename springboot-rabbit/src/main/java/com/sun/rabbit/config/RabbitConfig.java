package com.sun.rabbit.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class RabbitConfig {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ConnectionFactory connectionFactory;

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
    @Bean
    public Queue ackQueue(){
        return new Queue("ackQueue");
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
    @Bean
    public Binding bindingAckQueueExchange(Queue ackQueue,TopicExchange asyncExchange){
        return BindingBuilder.bind(ackQueue).to(asyncExchange).with("topic.ackQueueKey");
    }



    //消息发送到交换器时触发
    RabbitTemplate.ConfirmCallback msgConfirmCallback= (correlationData, b, s) -> {
        if(b){
            log.info("消息{}发送exchange成功",correlationData.getId());
            // 写自己的业务
        }else{
            log.info("消息发送到exchange失败，原因：{}",s);
        }
    };

    //消息从交换器发送到队列失败时触发
    RabbitTemplate.ReturnCallback msgReturnCallback= (message, i, s, s1, s2) -> log.info("消息：{}，错误码：{}，失败原因：{}，交换器：{}，路由key：{}",message.getMessageProperties().getCorrelationId(),i,s,s1,s2);

    @Autowired
    public RabbitTemplate rabbitTemplate(){
        //消息发送失败后返回到队列中
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(msgReturnCallback);
        rabbitTemplate.setConfirmCallback(msgConfirmCallback);

        return rabbitTemplate;
    }





}
