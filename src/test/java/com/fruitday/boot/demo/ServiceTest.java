package com.fruitday.boot.demo;

import com.fruitday.boot.BootApplication;
import com.fruitday.boot.dao.jpa.UserRepository;
import com.fruitday.boot.service.jpa.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ServiceTest {

    @Autowired
    private UserServiceImpl service;

    @MockBean
    private UserRepository userdao;

    @Before
    public void init() {
        given(userdao.count()).willReturn(1000l);
    }

    @Test
    public void serviceTest() {
        String moni = service.moni(10);
        Assert.assertEquals("期待结果:10:1000=" + moni, "10:1000", moni);
    }
}
