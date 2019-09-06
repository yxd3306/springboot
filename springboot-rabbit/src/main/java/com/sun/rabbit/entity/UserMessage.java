package com.sun.rabbit.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 喻湘东
 * @Create 2019-09-06 14:54:04
 */
@Data
public class UserMessage implements Serializable {

    private String userId;
    private String msg;

}
