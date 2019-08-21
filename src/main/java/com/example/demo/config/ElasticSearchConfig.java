package com.example.demo.config;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @Author: amy
 * @Date: 2019/8/19
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }

}
