package com.fruitday.boot.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面
 */
@Component
@Aspect
public class DemoAopAccessAspect {

    private static Logger log = LoggerFactory.getLogger(DemoAopAccessAspect.class);

    @Autowired
    private ObjectMapper mapper;

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.fruitday.boot.controller.*.*(..)) && !@annotation(com.fruitday.boot.config.aop.TestAccess)")
    public void webLog(){
        System.out.println("这是啥");
    }

    @Before("webLog()")//可以包含逻辑运算符
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("=================================");
        System.out.println("requestURL : " + request.getRequestURL().toString());
        System.out.println("queryString : " + request.getQueryString());
        System.out.println("remoteAddr IP : " + request.getRemoteAddr());
        System.out.println("remoteHost : " + request.getRemoteHost());
        System.out.println("remotePort : " + request.getRemotePort());
        System.out.println("localAddr : " + request.getLocalAddr());
        System.out.println("localName : " + request.getLocalName());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("parameters : " + request.getParameterMap());

        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("headers : " + mapper.writeValueAsString(getHeadersInfo(request)));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + ret);
        System.out.println("SPEND TIME : " + (System.currentTimeMillis() - startTime.get())+"ms");
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
//    @Around("webLog()")
    @Around("@within(org.springframework.stereotype.Controller) && !@annotation(com.fruitday.boot.config.aop.TestAccess)")
    public Object methodLogAop(final ProceedingJoinPoint point) throws Throwable {
        try {
            Object[] args = point.getArgs();
            log.info("args:{}", Arrays.asList(args));
            System.out.println("args:" + Arrays.asList(args));

            Object proceed = point.proceed();

            log.info("return:{}", proceed);
            System.out.println("return:" + proceed);
            return proceed;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
