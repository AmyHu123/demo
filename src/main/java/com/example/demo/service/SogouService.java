package com.example.demo.service;

import com.example.demo.dao.SogouDao;
import com.example.demo.model.SogouDict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Author: amy
 * @Date: 2019/7/29
 */
@Service
public class SogouService {

    @Resource
    private SogouDao sogouDao;

    /**
     * 读取文件内容
     */
    public void readDict(String path,String category){
        String line = StringUtils.EMPTY;
        File file = new File(path);
        BufferedReader br = null;
        String[] splitted = null;
        String pinyin = StringUtils.EMPTY;
        String word = StringUtils.EMPTY;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while ((line = br.readLine())!= null) {
                if(!line.equals("")){
                    splitted = line.split(" ");
                    pinyin = splitted[0];
                    word = splitted[1];
                    sogouDao.saveSogou(new SogouDict(category,pinyin,word,System.currentTimeMillis()));
                }


            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public long getInfo(String name){
         return sogouDao.getByName(name);
    }
}
