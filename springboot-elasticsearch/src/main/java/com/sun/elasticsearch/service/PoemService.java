package com.sun.elasticsearch.service;

import com.sun.elasticsearch.entity.Poem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-17 23:18:11
 * @describe
 */
public interface PoemService {
    //保存Poem实体
    void save(Poem poem);

    //基于title和content进行搜索，返回分页
    Page<Poem> search(String title, String content, Pageable pageable);

    //基于content进行搜索，返回分页
    Page<Poem> search(String content, Pageable pageable);

    //返回所有数据集合
    Page<Poem> findAll(Pageable pageable);
}
