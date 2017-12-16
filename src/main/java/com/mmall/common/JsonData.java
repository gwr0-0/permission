package com.mmall.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gwr0-0 on 2017/11/13.
 */
@Getter
public class JsonData {
    private boolean ret;
    private String msg;
    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    public JsonData(boolean ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public JsonData(boolean ret, Object data) {
        this.ret = ret;
        this.data = data;
    }

    public JsonData(boolean ret, String msg, Object data) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }

    public static JsonData success() {
        return new JsonData(true);
    }

    public static JsonData success(Object data) {
        return new JsonData(true, data);
    }

    public static JsonData success(String msg, Object data) {
        return new JsonData(true, msg, data);
    }

    public static JsonData fail(String msg) {
        return new JsonData(false, msg);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>(2);
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
