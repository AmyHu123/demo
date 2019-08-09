package com.example.demo.model;

import com.example.demo.utils.IDUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: amy
 * @Date: 2019/8/9
 */
@Document(collection = "dfs_data")
@Data
@NoArgsConstructor
public class DfsData {

    @Id
    private String id;

    /** 密码 **/
    private String security;

    /** 文件存储路径 **/
    private String filePath;

    /** 分布式唯一Id **/
    private String uniqueId;

    public DfsData(String security,String filePath){
       this.filePath = filePath;
       this.security = security;
       this.uniqueId = IDUtil.generate();
    }
}
