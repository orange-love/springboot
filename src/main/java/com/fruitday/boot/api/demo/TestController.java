package com.fruitday.boot.api.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping("/demo")
    public String getTest() {
        RestTemplate template = restTemplateBuilder.build();
        String url = "http://localhost:9000/parser";

//        获得list数据
//        ParameterizedTypeReference<List<User>> typeReference = new ParameterizedTypeReference<List<User>>(){};
//        HttpEntity body = null;
//        ResponseEntity<List<User>> list = template.exchange(url, HttpMethod.GET, body, typeReference);

//        额外获得http头信息
//        template.getForEntity();
        String object = template.getForObject(url, String.class);
        return object;
    }
}