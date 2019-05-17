package com.sun.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-17 23:15:55
 * @describe
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.sun.elasticsearch.dao") // 将es操作接口注入到spring容器
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class,args);
    }

}
