package com.fruitday.boot.controller.valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Size;

@Controller
@Validated
public class ValidMethodController {

    /**
     * 对方法简单参数的验证
     * @param name
     * @return
     */
    @GetMapping("/valid")
    @ResponseBody
    public String valid(@Size(min = 2,max=5,message = "valid出异常了") String name) {
        return "hello world! By boot valid" + name;
    }
}
