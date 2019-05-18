package com.sun.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-18 18:27:10
 * @describe
 */
@Data
public class Book {
    @Id
    private String id;
    //价格
    private Integer price;
    //书名
    private String name;
    //简介
    private String info;
    //出版社
    private String publish;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
}
