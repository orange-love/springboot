package com.fruitday.boot.demo;

import com.fruitday.boot.service.jpa.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    @Test
    public void test() {
        int num = 10;
        String expectd = "1000";

        //创建虚拟对象，即mock对象
        UserServiceImpl userService = mock(UserServiceImpl.class);

        //没有模拟的方法无法调用
        //模拟mock对象调用，传入任何int都返回1000
//        when(userService.moni(anyInt())).thenReturn("1000");
        //指定入参，不符合则结果异常
        when(userService.moni(eq(num))).thenReturn("1000");
        //对入参进行判断
        //模拟方法抛出异常
//        when(userService.moni(gt(5))).thenThrow(new IllegalArgumentException("参数超出最大值"));

        //模拟入参类型
//        when(userService.moni(any(int.class))).thenReturn("1000");


        //自定义进制某方法被调用
//        doThrow(new UnsupportedOperationException("不支持该方法")).when(userService).moni(anyInt());

        //实际调用
        String moni = userService.moni(num);
        //验证调用次数的时候需要有参数接收返回值
        moni = userService.moni(num);
        //对结果断言比较
        assertEquals(expectd, moni);

        //模拟方法的调用次数
        verify(userService, times(2)).moni(anyInt());

        //模拟方法的调用顺序
//        InOrder order = inOrder(userService);
//        order.verify(userService).moni(num);
//        order.verify(userService).moni(num);

    }
}
