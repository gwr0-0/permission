package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gwr0-0
 * @date 2017/12/16
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    public JsonData hello() {
        log.info("hello");
        throw new PermissionException("test error");
        //throw new RuntimeException("test error");
        //return JsonData.success("hello, permission");
    }

}
