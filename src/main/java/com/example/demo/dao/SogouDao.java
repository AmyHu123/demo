package com.example.demo.dao;

import com.example.demo.model.SogouDict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: amy
 * @Date: 2019/7/29
 */
@Repository
@Slf4j
public class SogouDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveSogou(SogouDict sogouDict){
        try {
            mongoTemplate.save(sogouDict);
        }catch (Exception e){

        }
    }

    public long getByName(String word){
        Criteria criteria =Criteria.where("word").is(word);
        Query query = new Query(criteria);
        return mongoTemplate.count(query,SogouDict.class);
    }
}
