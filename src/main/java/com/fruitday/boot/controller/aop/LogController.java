package com.fruitday.boot.controller.aop;

import com.fruitday.boot.config.aop.TestAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogController {

    @RequestMapping("/second")
    @TestAccess(desc = "second")
    @ResponseBody
    public Object second() {
        return "second controller";
    }

}
