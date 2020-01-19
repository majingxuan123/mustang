package com.springcloudstudy.mustangbase.utils.jsonutils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <ul>
 * <li>文件名称: JsonUtil</li>
 * <li>文件描述:</li>
 * <li>版权所有: 版权所有(C) 2017</li>
 * <li>公 司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要:</li>
 * <li>其他说明:</li>
 * <li>创建日期:2018/3/14 0014</li>
 * </ul>
 * <ul>
 * <li>修改记录:</li>
 * <li>版 本 号:</li>
 * <li>修改日期:</li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author majx
 * @version 1.0.0
 */
public class JsonUtil {

    /**
     */
    public static String objToJson(Object obj) {
        return JSONObject.toJSONString(obj);
    }
    /**
     *
     */
    public static <T> T jsonToObj(String jsonString, Class<T> valueType) {
        T t = JSONObject.parseObject(jsonString, valueType);
        return t;
    }
    /**
     *
     *
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> valueType) {
        return JSONArray.parseArray(jsonString,valueType);
    }
    /**
     *
     */
    public static JSONObject toJsonObject(String jsonStr) {
        return JSONObject.parseObject(jsonStr);
    }

    /**
     */
    public static JSONArray getJsonArray(String JsonArrayStr) {
        return JSONArray.parseArray(JsonArrayStr);
    }

}
