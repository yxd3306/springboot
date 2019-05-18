package com.sun.mongo.dao;

import com.sun.mongo.entity.Book;
import org.springframework.stereotype.Repository;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-18 18:41:00
 * @describe
 */
@Repository
public class BookMongoDbDao extends MongoDbDao<Book> {
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
