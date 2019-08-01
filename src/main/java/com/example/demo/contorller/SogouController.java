package com.example.demo.contorller;

import com.example.demo.service.SogouService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: amy
 * @Date: 2019/7/29
 */
@RestController
@Slf4j
public class SogouController {

    @Resource
    private SogouService sogouService;

    @RequestMapping("/save")
    public void saveSogouInfo(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            List<File> Iterator = (List<File>) FileUtils.listFiles(new File("D:\\搜狗词库"),new String[]{"txt"},true);

            Iterator.forEach(o ->{
            final String oldPath = o.toString().replaceAll("\\\\","/");
            String newPath = oldPath.substring(oldPath.indexOf("搜狗词库") + 5,oldPath.lastIndexOf("/"));

            executorService.execute(() -> sogouService.readDict(oldPath,newPath));
        });
            executorService.shutdown();
        } catch (Exception e) {
           log.error("exception: [同步信息出错]",e);
        }
    }

    @RequestMapping("/test")
    public long getInfo(@RequestParam("name") String name){
          log.info("查询的参数为:" + name);
          return sogouService.getInfo(name);
    }
}
