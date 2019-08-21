package com.example.demo.contorller;

import com.example.demo.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @Author: amy
 * @Date: 2019/8/19
 */
@RestController
@Slf4j
public class EsController {

    @Autowired
    private EsService esService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 添加数据
     */
    @GetMapping("/insert")
    public void insertIndex(){
        esService.insert();
    }

    /**
     * 查询出所有的数据
     * @return
     */
    @GetMapping("/all")
    public List<Map<String, Object>> searchAll(){
        Client client = elasticsearchTemplate.getClient();
        SearchRequestBuilder srb = client.prepareSearch("docinfo").setTypes("docs");
        SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(); // 查询所有
        return searchFunction(sr);
    }

    /**
     * 根据字段查询
     * @return
     */
    @GetMapping("/search")
    public List<Map<String, Object>> searchByKey(String name){
        Client client = elasticsearchTemplate.getClient();
        SearchRequestBuilder srb = client.prepareSearch("docinfo").setTypes("docs");
        SearchResponse sr= srb.setQuery(QueryBuilders.boolQuery().must(matchQuery("brand",name))).execute().actionGet();

        return searchFunction(sr);
    }

    private List<Map<String, Object>> searchFunction(SearchResponse sr) {
        SearchHits hits = sr.getHits();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSourceAsMap();
            list.add(source);
            log.info(hit.getSourceAsString());
        }
        return list;
    }
    
}
