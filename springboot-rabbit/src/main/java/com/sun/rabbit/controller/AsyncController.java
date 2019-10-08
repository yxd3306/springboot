package com.sun.rabbit.controller;

import com.sun.rabbit.entity.MessageContainer;
import com.sun.rabbit.entity.UserMessage;
import com.sun.rabbit.producer.AsyncCreateOrderSend;
import com.sun.rabbit.queue.SumQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 09:27:02
 */
//@Controller
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncCreateOrderSend asyncCreateOrderSend;
    @Autowired
    private MessageContainer messageContainer;

    private static int threads = 1000;
    private static CountDownLatch cdl = new CountDownLatch(threads);
    private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private static Lock lock = new ReentrantLock();


    @GetMapping("/asyncOrder")
    @ResponseBody
    public Callable<DeferredResult<Object>> createOrder(String userId) {

        return () -> {
            DeferredResult<Object> result = new DeferredResult<>((long) 3000, "create fail ......");
            final Map<String, DeferredResult> resultMap = messageContainer.getUserMess();
            resultMap.put(userId, result);
            result.onCompletion(() -> {
                log.info("resultMap：{},userId：{}", resultMap, userId);
            });
            UserMessage userMessage = new UserMessage();
            userMessage.setUserId(userId);
            userMessage.setMsg("创建订单");
            asyncCreateOrderSend.send(userMessage);
            return result;
        };

    }

    /*@GetMapping("/asyncOrder")
    @ResponseBody
    public DeferredResult<Object> createOrder(String userId){

        Map<String, Object> map = new ConcurrentHashMap<>();
        MyDeferredResult<Object> deferredResult = MyDeferredResult.getInstance();
        map.put("deferredResult",deferredResult);
        map.put("msg",userId);
        log.info("AsyncController中deferredResult内存地址为：{}",map.get("deferredResult").hashCode());
        asyncCreateOrderSend.send(map);
        return deferredResult;

    }*/
    @GetMapping("/asyncOrder2")
    @ResponseBody
    public DeferredResult<Object> createOrder2(String userId) {

        MyDeferredResult<Object> deferredResult = new MyDeferredResult<>((long) 5000, "create fail ......");
        SumQueue.save(deferredResult);
        new Thread(() -> {
            DeferredResult<Object> objectDeferredResult = SumQueue.get();
            objectDeferredResult.setResult("订单创建成功");
        }).start();
        return deferredResult;

    }


    @GetMapping("/asyncOrder3")
    @ResponseBody
    public Callable<DeferredResult<Object>> createOrder3() {


        return () -> {
            for (int i = 0; i < threads; i++) {
                Task task = new Task(UUID.randomUUID().toString());
                pool.submit(task);
                cdl.countDown();
            }
            return null;
        };

    }

    class Task implements Callable<Object> {
        private String userId;

        public Task(String userId) {
            this.userId = userId;
        }

        public Task() {
        }

        @Override
        public Object call() throws InterruptedException {
            cdl.await();
            DeferredResult<Object> result = new DeferredResult<>((long) 3000, "create fail ......");
            final Map<String, DeferredResult> resultMap = messageContainer.getUserMess();
            resultMap.put(userId, result);
            UserMessage userMessage = new UserMessage();
            userMessage.setUserId(userId);
            userMessage.setMsg("创建订单");
            result.onCompletion(() ->
                    log.info("resultMap：{}，userId：{}", resultMap, userId)
            );
            asyncCreateOrderSend.send(userMessage);
            return result;
        }
    }
}

