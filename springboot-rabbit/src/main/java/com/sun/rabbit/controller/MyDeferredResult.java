package com.sun.rabbit.controller;

import org.springframework.web.context.request.async.DeferredResult;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 10:31:26
 */
public class MyDeferredResult<T> extends DeferredResult implements Serializable {
    public MyDeferredResult() {
        super();
    }

    public MyDeferredResult(Long timeout) {
        super(timeout);
    }

    public MyDeferredResult(Long timeout, Object timeoutResult) {
        super(timeout, timeoutResult);
    }

    @Override
    public boolean hasResult() {
        return super.hasResult();
    }

    @Override
    public Object getResult() {
        return super.getResult();
    }

    @Override
    public void onTimeout(Runnable callback) {
        super.onTimeout(callback);
    }

    @Override
    public void onError(Consumer callback) {
        super.onError(callback);
    }

    @Override
    public void onCompletion(Runnable callback) {
        super.onCompletion(callback);
    }

    @Override
    public boolean setResult(Object result) {
        return super.setResult(result);
    }

    @Override
    public boolean setErrorResult(Object result) {
        return super.setErrorResult(result);
    }
}
