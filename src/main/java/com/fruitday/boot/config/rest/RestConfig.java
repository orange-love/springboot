package com.fruitday.boot.config.rest;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig implements RestTemplateCustomizer {
    /**
     * 使用默认的jdk的URLConnection作为底层的HTTP工具
     * 其他工具如OkHttp
     * @param restTemplate
     */
    @Override
    public void customize(RestTemplate restTemplate) {
        SimpleClientHttpRequestFactory jdkhttp = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        jdkhttp.setConnectTimeout(10000);
    }
}
