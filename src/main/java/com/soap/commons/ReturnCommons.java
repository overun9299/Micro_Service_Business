package com.soap.commons;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 返回合集
 * @Author ZhangPY
 * @Date 2020/6/16
 */
public class ReturnCommons {

    /**
     * 请求成功
     * @return
     */
    public static String getSuccess() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("msg", "请求成功");
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 请求成功
     * @param msg 信息
     * @return
     */
    public static String getSuccess(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("msg", msg);
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 请求成功
     * @param data 数据
     * @return
     */
    public static String getSuccess(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("msg", "请求成功");
        result.put("data", data);
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 请求成功
     * @param data 数据
     * @param msg 信息
     * @return
     */
    public static String getSuccess(Object data, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("msg", msg);
        result.put("data", data);
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 请求失败
     * @return
     */
    public static String getFail() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("msg", "请求失败");
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 请求失败
     * @param msg 信息
     * @return
     */
    public static String getFail(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("msg", msg);
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 请求失败
     * @param data 数据
     * @return
     */
    public static String getFail(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("msg", "请求失败");
        result.put("data", data);
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 请求失败
     * @param msg 信息
     * @param data 数据
     * @return
     */
    public static String getFail(String msg, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("msg", msg);
        result.put("data", data);
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
