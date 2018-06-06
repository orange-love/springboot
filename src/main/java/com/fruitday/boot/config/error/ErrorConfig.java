package com.fruitday.boot.config.error;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * 全局异常处理
 * 将抛出到tomcat的异常进行处理
 * 如果NoHandlerFound，设置抛出异常
 */
@Configuration
public class ErrorConfig {

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        dispatcherServlet.setDetectAllHandlerExceptionResolvers(true);
        return registration;
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionResolver();
    }
}
