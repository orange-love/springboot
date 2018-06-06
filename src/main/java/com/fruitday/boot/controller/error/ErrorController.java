package com.fruitday.boot.controller.error;

import com.fruitday.boot.domain.error.BusinessException;
import com.fruitday.boot.domain.error.MyException;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @GetMapping("/error1")
    public String error1() throws Exception {
        throw new Exception("发生错误Exception");
    }

    @RequestMapping("/error2")
    public String error2() throws MyException {
        throw new MyException("发生错误MyException");
    }

    @GetMapping("/error3")
    public String error3() throws NotFoundException {
        throw new NotFoundException("发生错误NotFoundException");
    }

    @GetMapping("/error4")
    public String error4() throws BusinessException {
        throw new BusinessException("发生错误BusinessException");
    }

    @GetMapping("/errors")
    public String errors() {
        throw new RuntimeException("发生错误RuntimeException");
    }
}
