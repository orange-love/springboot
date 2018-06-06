package com.fruitday.boot.config.web;

import com.fruitday.boot.config.web.interceptors.ControllerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 格式化
     * @param formatterRegistry
     */
    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 拦截器
     *  多个拦截器组成一个拦截器链
     *  addPathPatterns 用于添加拦截规则
     *  excludePathPatterns 用于排除拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new SessionHandlerceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 跨域访问配置
     * 发起的跨域请求。浏览器会对请求域返回的响应信息检查http头，如果Access-Control-Allow-Origin包含了自身域，则表示允许访问，否则报错，这就是allowedOrigins的作用
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:9000")
                .allowedMethods("POST", "GET");
    }

    /**
     * URL到视图到的映射
     * @param viewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
//        viewControllerRegistry.addViewController("/").setViewName("/hi");
    }

}