package com.mmall.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *（1）preHandle: 在执行controller处理之前执行，返回值为boolean ,返回值为true时接着执行postHandle和afterCompletion，如果我们返回false则中断执行
 *（2）postHandle:在执行controller的处理后，在ModelAndView处理前执行。正常返回才会执行
 *（3）afterCompletion ：在DispatchServlet执行完ModelAndView之后执行。任何情况下都会执行
 *
 * 记录接口执行时间，毫秒级
 * @author gwr0-0
 * @date 2017/12/18
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
        log.info("request start, url:{}", url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String url = request.getRequestURI().toString();
//        Long start = (Long)request.getAttribute(START_TIME);
//        Long end = System.currentTimeMillis();
//        log.info("request finished, url:{}, cost:{}", url, end - start);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        Long start = (Long)request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("request completion, url:{}, cost:{}", url, end - start);
    }
}
