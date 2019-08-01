package com.example.demo.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: amy
 * @Date: 2019/7/29
 */
@Document(collection = "sogou_dict")
@Data
@NoArgsConstructor
public class SogouDict {

    private String id;

    /** 类别 **/
    private String category;

    private String pinyin;

    /** 词 **/
    private String word;

    /** 创建时间 **/
    private long createTime;

    public SogouDict(String category, String pinyin, String word, long createTime) {
        this.category = category;
        this.word = word;
        this.createTime = createTime;
        this.pinyin = pinyin;
    }

}
