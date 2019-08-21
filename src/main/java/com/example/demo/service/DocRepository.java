package com.example.demo.service;

import com.example.demo.model.DocInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: amy
 * @Date: 2019/8/19
 */
public interface DocRepository extends ElasticsearchRepository<DocInfo,Long> {
}
