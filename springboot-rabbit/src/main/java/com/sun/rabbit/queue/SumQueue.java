package com.sun.rabbit.queue;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 10:56:14
 */
public class SumQueue {

    private static Queue queue = new ConcurrentLinkedQueue();

    public static void save(DeferredResult<Object> deferredResult){
        queue.add(deferredResult);
    }

    public static DeferredResult<Object> get(){
        return (DeferredResult<Object>) queue.poll();
    }

}
