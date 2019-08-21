package com.example.demo.service;

import com.example.demo.model.DocInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: amy
 * @Date: 2019/8/19
 */
@Service
@Slf4j
public class EsService {

    @Resource
    private DocRepository docRepository;

    public void insert() {
        DocInfo docInfo = new DocInfo(7l, "小米手机", "手机", "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        docRepository.save(docInfo);
    }

}
