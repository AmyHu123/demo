package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: amy
 * @Date: 2019/8/19
 * 注：在修改该类属性的FieldType的时候,一定要确认es中的标签的template,否则会报错
 */
@Document(indexName = "docinfo",type = "docs", shards = 1, replicas = 0)
@Data
@NoArgsConstructor
public class DocInfo {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String title; //标题

    @Field(type = FieldType.Keyword)
    private String category;// 分类

    @Field(type = FieldType.Keyword)
    private String brand; // 品牌

    @Field(type = FieldType.Double)
    private Double price; // 价格

    @Field(index = false, type = FieldType.Keyword)
    private String images; // 图片地址

    public DocInfo(Long id, String title, String category, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }
}


