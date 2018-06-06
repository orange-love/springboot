package com.fruitday.boot.api.demo;

import com.fruitday.boot.config.aop.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Log("api log 测试")
    @GetMapping("/log")
    public String logTest(String name,int age) {
        return "log test" + name + ":" + age;
    }
}