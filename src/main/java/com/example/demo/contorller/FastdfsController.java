package com.example.demo.contorller;

import com.example.demo.common.ServerResponse;
import com.example.demo.service.FastdfsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: amy
 * @Date: 2019/8/9
 */
@RestController
@Slf4j
public class FastdfsController {

    @Autowired
    private FastdfsService fastdfsService;

    /**
     * 上传文件
     * @param fileType
     * @param callback_url
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public ServerResponse uploadFile(@RequestParam String fileType,
                             @RequestParam(required = false) String callback_url,
                             @RequestParam MultipartFile file,
                             @RequestParam String security){
        try {
            return ServerResponse.createBySuccess(fastdfsService.upload(fileType,file.getInputStream(),security));
        } catch (Exception e) {
            log.error("上传文件异常,params:{fileType:" + fileType  + ",security:" + security + "}",e);
        }
        return ServerResponse.createByErrorMessage("上传文件失败");
    }

    /**
     * 下载文件
     * @param security
     * @return
     */
    @GetMapping("/download")
    public ServerResponse downloadPath(@RequestParam String security){
        String path = fastdfsService.downloadPath(security);
        if(path == null){
           return ServerResponse.createByErrorMessage("所需要下载的文件不存在");
        }
        return ServerResponse.createBySuccess(path);
    }

}
