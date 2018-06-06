package com.fruitday.boot.config.web.interceptors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义日志拦截器
 *  用于多线程
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在请求处理之前回调方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUUID = MDC.get("requestUUID");
        if (StringUtils.isBlank(requestUUID)) {
//            String uuid = UUID.randomUUID().toString();
            String uuid = String.valueOf(Thread.currentThread().getId());

            uuid = uuid.replaceAll("-", "").toUpperCase();
            MDC.put("requestUUID", uuid);
            logger.info("ControllerInterceptor preHandle 在请求处理之前生成 logback requestUUID:{}", uuid);
        }
        /* 只有返回true才会继续向下执行，返回false取消当前请求 */
        return true;
    }

    /**
     * 请求处理之后回调方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /* 线程结束后需要清除,否则当前线程会一直占用这个requestId值 */
        MDC.remove("requestUUID");
        logger.info("ControllerInterceptor postHandle 请求处理之后清除 logback MDC requestUUID");
    }

    /**
     * 整个请求处理完毕回调方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*整个请求线程结束后需要清除,否则当前线程会一直占用这个requestId值 */
        MDC.clear();
        logger.info("ControllerInterceptor afterCompletion 整个请求处理完毕清除 logback MDC requestUUID");
    }
}
