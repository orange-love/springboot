package com.fruitday.boot.controller.jpa;

import com.fruitday.boot.service.jpa.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class JpaController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/test")
    @ResponseBody
    public String test(int num) {
        String moni = userService.moni(num);
        return "Controller:" + moni;
    }
}
