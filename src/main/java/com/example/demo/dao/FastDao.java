package com.example.demo.dao;

import com.example.demo.model.DfsData;
import com.example.demo.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


/**
 * @Author: amy
 * @Date: 2019/8/9
 */
@Repository
@Slf4j
public class FastDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(DfsData dfsData) {
        try {
            if(getBySecurity(dfsData.getSecurity()) == null){
                mongoTemplate.save(dfsData);
            }else{
                //存在就更新文件
                Update update = new Update();
                update.set("filePath",dfsData.getFilePath());
                mongoTemplate.findAndModify(new Query(Criteria.where("security").is(dfsData.getSecurity())), update, DfsData.class);
            }

        } catch (Exception e) {
            log.error("插入上传数据失败,params:" + GsonUtil.toJson(dfsData),e);
        }
    }

    /**
     * 根据security查询上传的文件路径
     * @return
     */
    public DfsData getBySecurity(String security){
        Criteria criteria = Criteria.where("security").is(security);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query,DfsData.class);
    }
}
