package com.fruitday.boot.controller.redis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/redis")
public class RedisController {

    @GetMapping("/")
    public void say() {

    }
}
