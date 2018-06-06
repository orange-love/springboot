package com.fruitday.boot.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAopAccessAspect {

    @Pointcut(value = "@annotation(com.fruitday.boot.config.aop.TestAccess)")
    public void access() {

    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("second before");
    }

    @Around("access()")
    public Object around1(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

//    @Around("@annotation(testAccess)")
//    public Object around(ProceedingJoinPoint pjp, TestAccess testAccess) {
//        //获取注解里的值
//        System.out.println("second around:" + testAccess.desc());
//        try {
//            return pjp.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            return null;
//        }
//    }
}
