package com.fruitday.boot.controller.valid;

import com.fruitday.boot.domain.valid.WorkTest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class ValidController {

    /**
     * 对封装的Bean进行验证
     * @param test
     * @param result
     * @return
     */
    @GetMapping("/valids")
    @ResponseBody
    public String valids(@Valid WorkTest test, BindingResult result) {
        return "hello world! By boot valids"+result.hasErrors()+";"+(result.hasErrors()?result.getFieldError().getDefaultMessage():"ok");
    }

    /**
     * 不可行
     * @param test
     * @return
     */
//    @GetMapping("/valids1")
//    @ResponseBody
    public String valids1(@Validated WorkTest test) {
        return "hello world! By boot valids" + test.getName();
    }
}
