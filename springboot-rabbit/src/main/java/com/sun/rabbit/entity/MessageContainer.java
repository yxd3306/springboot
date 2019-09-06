package com.sun.rabbit.entity;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 14:45:31
 */
@Component
public class MessageContainer {

    private Map<String, DeferredResult> map = new ConcurrentHashMap<>();

    public Map<String, DeferredResult> getUserMess(){
        return map;
    }

}
