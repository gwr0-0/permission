package com.mmall.common;

import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gwr0-0 on 2017/11/13.
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {


    private static final String URL_JSON = ".json";
    private static final String URL_PAGE = ".page";

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {

        String url = httpServletRequest.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "system error";

        // 这里我们要求项目中
        // 所有请求json数据，都使用.json结尾
        // 所有请求page页面，都使用.page结尾
        if (url.endsWith(URL_JSON)) {
            if (e instanceof PermissionException) {
                JsonData result = JsonData.fail(e.getMessage());
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("unknown json exception , url:" + url, e);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        } else if (url.endsWith(URL_PAGE)) {
            log.error("unknown page exception, url:" + url, e);
            JsonData result = JsonData.fail(defaultMsg);
            //跳转到exception.jsp
            mv = new ModelAndView("exception", result.toMap());
        } else {
            log.error("unknown exception, url:" + url, e);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", result.toMap());
        }

        return mv;
    }
}
