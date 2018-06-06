package com.fruitday.boot.redis;

import com.fruitday.boot.BootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws Exception {
        // 保存字符串
        redisTemplate.boundValueOps("aaaa").set("22222");
        System.out.println(redisTemplate.boundValueOps("aaaa").get());
//        stringRedisTemplate.opsForValue().set("aaa", "111");
//        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void test1() throws Exception {

        // 保存对象
//        User user = new User("超人", 20);
//        redisTemplate.opsForValue().set(user.getName(), user);
//
//        user = new User("蝙蝠侠", 30);
//        redisTemplate.opsForValue().set(user.getName(), user);
//
//        user = new User("蜘蛛侠", 40);
//        redisTemplate.opsForValue().set(user.getName(), user);

//        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
//        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

    }
}
