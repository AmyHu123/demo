package com.example.demo.service;

import com.example.demo.dao.FastDao;
import com.example.demo.model.DfsData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: amy
 * @Date: 2019/8/9
 */
@Service
@Slf4j
public class FastdfsService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${fdfs.web-server-url}")
    private String uploadDomain;

    @Autowired
    private FastDao fastDao;

    public String upload(String fileType,InputStream stream,String security) throws IOException {
           int fileSize = stream.available();
           StorePath path = fastFileStorageClient.uploadFile(stream, fileSize, fileType, null);
           //存储请求路径
           fastDao.save(new DfsData(security,path.getFullPath()));
           return uploadDomain + path.getFullPath();
       }

    /**
     * 获取文件下载路径
     * @param security
     * @return
     */
    public String downloadPath(String security){
        try {
            DfsData dfsData = fastDao.getBySecurity(security);
            if (dfsData != null) {
                return uploadDomain + dfsData.getFilePath();
            }

        }catch (Exception e){
            log.error("查询文件地址异常,params:" + security,e);
        }
        return null;
    }
}
