package com.sun.elasticsearch.dao;

import com.sun.elasticsearch.entity.Poem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-17 23:17:38
 * @describe
 */
public interface PoemRepository extends ElasticsearchRepository<Poem,Long> {
    Page<Poem> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
}
