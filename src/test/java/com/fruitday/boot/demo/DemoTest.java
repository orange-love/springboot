package com.fruitday.boot.demo;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BootApplication.class)
public class DemoTest {

    @BeforeClass
    public static void beforeClassTest() {
        System.out.println("=====单元测试开始之前执行初始化========");
    }

    @Before
    public void beforeTest() {
        System.out.println("=====单元测试方法开始之前执行========");
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = sdf.parse("2018-05-16");
        Date end = sdf.parse("2018-05-22");

        System.out.println("相差天数:" + begin.compareTo(end));
        Assert.assertEquals("相差天数", -1, begin.compareTo(end));

    }

    @After
    public void afterTest() {
        System.out.println("=====单元测试方法结束后执行========");
    }

    @AfterClass
    public static void afterClassTest() {
        System.out.println("=====单元测试结束后销毁========");
    }

}
