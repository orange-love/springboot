package com.fruitday.boot.demo;

import com.fruitday.boot.controller.jpa.JpaController;
import com.fruitday.boot.service.jpa.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(JpaController.class)//需要测试的Controller
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    @Before
    public void init() {
        given(userService.moni(Mockito.anyInt())).willReturn("100");
    }

    @Test
    public void test() throws Exception {
        int num = 20;
        String expectd = "Controller:100";
        mvc.perform(MockMvcRequestBuilders.get("/user/test?num={num}", num))
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectd)));
    }
}
