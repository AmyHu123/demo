package com.example.demo.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @Value(value = "${fdfs.web-server-url}")
    private String uploadDomain;

    @RequestMapping("/hello")
    public String hello() {
        return uploadDomain + "Hello World!";
    }

}
