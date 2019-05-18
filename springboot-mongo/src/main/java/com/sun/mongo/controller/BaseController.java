package com.sun.mongo.controller;

import com.sun.mongo.entity.Book;
import com.sun.mongo.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-18 18:28:31
 * @describe
 */
@RestController
public class BaseController {

    @Autowired
    private MongoDbService mongoDbService;

    @PostMapping("/mongo/save")
    public String saveObj(@RequestBody Book book) {return mongoDbService.saveObj2(book);}

    @GetMapping("/mongo/queryById")
    public Book findAll(Integer id) {return mongoDbService.queryById(id);}

}
