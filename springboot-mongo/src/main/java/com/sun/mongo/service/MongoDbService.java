package com.sun.mongo.service;

import com.sun.mongo.dao.BookMongoDbDao;
import com.sun.mongo.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-18 18:27:46
 * @describe
 */
@Service
public class MongoDbService {
    private static final Logger logger = LoggerFactory.getLogger(MongoDbService.class);

    //注入新的CURD操作类
    @Autowired
    private BookMongoDbDao bookMongoDbDao;

    /**
     * 保存对象
     *
     * @param book
     * @return
     */
    public String saveObj2(Book book) {
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        //调用bookMongoDbDao父类中的添加方法
        bookMongoDbDao.save(book);
        return "添加成功";
    }

    public Book queryById(Integer id){
        return bookMongoDbDao.queryById(id);
    }



}
