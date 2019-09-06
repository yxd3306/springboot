package com.sun.rabbit.consumer;

import com.sun.rabbit.controller.MyDeferredResult;
import com.sun.rabbit.entity.MessageContainer;
import com.sun.rabbit.entity.UserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 09:49:55
 * 用于接收响应结果的消费者
 */
@Component
@RabbitListener(queues = "asyncResponseQueue")
@Slf4j
public class AsyncResponseConsumer {

    @Autowired
    MessageContainer messageContainer;

    @RabbitHandler
    public void process(UserMessage userMessage) {
        Map<String, DeferredResult> userMess = messageContainer.getUserMess();
        DeferredResult deferredResult = userMess.get(userMessage.getUserId());
        log.info("AsyncResponseConsumer消息消费：{}",userMessage,deferredResult);
        deferredResult.setResult(userMessage.getMsg());
        userMess.remove(userMessage.getUserId());

    }

}
