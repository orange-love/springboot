package com.fruitday.boot.common.error;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

/**
 * 没有实现
 */

/**
 *
 * 写一个类，实现ErrorPageRegistrar接口，然后实现registerErrorPages方法，在该方法里面，添加具体的错误处理逻辑(类似web.xml里面配置错误处理方式)，这一种也是全局的异常处理
 */
//@Component
public class MyErrorPageRegistrar implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //具体的错误码错误异常页面
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html");
//        //指定具体异常的错误定制页面
//        ErrorPage argspage = new ErrorPage(IllegalArgumentException.class,"/argsException.html");
//        registry.addErrorPages(e404,e500,argspage);
        registry.addErrorPages(e404, e500);
    }
}
