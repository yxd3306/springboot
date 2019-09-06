package com.sun.rabbit;

import com.sun.rabbit.entity.MessageContainer;
import com.sun.rabbit.entity.UserMessage;
import com.sun.rabbit.producer.AsyncCreateOrderSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 15:14:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    @Autowired
    private AsyncCreateOrderSend asyncCreateOrderSend;
    @Autowired
    private MessageContainer messageContainer;

    private static int threads = 100;
    private static CountDownLatch cdl = new CountDownLatch(threads);
    private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);


    @Test
    public void createOrder() throws InterruptedException {
        for (int i = 0; i < threads; i++) {
            Task task = new Task("" + i + 100);
            Thread thread = new Thread(task);
            pool.execute(thread);
            cdl.countDown();
        }

        Thread.sleep(50000);
    }

    class Task implements Runnable{
        private String userId;

        public Task(String userId) {
            this.userId = userId;
        }

        public Task() {
        }

        @Override
        public void run() {
            try {
                cdl.await();
                DeferredResult<Object> result = new DeferredResult<>((long) 3000,"create fail ......");
                final Map<String, DeferredResult> resultMap = messageContainer.getUserMess();
                resultMap.put(userId, result);
                result.onCompletion(() -> resultMap.remove(userId));
                UserMessage userMessage = new UserMessage();
                userMessage.setUserId(userId);
                userMessage.setMsg("创建订单");
                asyncCreateOrderSend.send(userMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
