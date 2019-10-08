package com.sun.rabbit.consumer;

import com.sun.rabbit.entity.MessageContainer;
import com.sun.rabbit.entity.UserMessage;
import com.sun.rabbit.producer.AsyncResponseSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 09:35:34
 * 用于处理业务逻辑的消费者
 */
//@Component
@RabbitListener(queues = "asyncCreateOrderQueue")
@Slf4j
public class AsyncCreateOrderConsumer {

    @Autowired
    private AsyncResponseSend asyncResponseSend;

    @RabbitHandler
    public void process(UserMessage userMessage) throws InterruptedException {

        String userId = userMessage.getUserId();

        log.info("用户：{} 创建订单成功",userId);
        userMessage.setMsg("创建订单成功");
        Thread.sleep((new Random().nextInt(3)+1)*100);
        log.info("AsyncCreateOrderConsumer消息消费：{}",userMessage);
        asyncResponseSend.send(userMessage);


    }

}
