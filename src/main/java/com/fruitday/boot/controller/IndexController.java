package com.fruitday.boot.controller;

import com.fruitday.boot.service.demo.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private TestService testService;

    @RequestMapping("/hi")
    @ResponseBody
    public String hi() {
        String ss = testService.ss();
        return "hello world! By boot" + ss;
    }

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return "hello world! By boot get";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(String name) {
        return "hello world! By boot test: " + name;
    }
}
