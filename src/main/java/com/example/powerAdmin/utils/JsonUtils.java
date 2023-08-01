package com.example.powerAdmin.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 设置序列化时忽略值为null的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置反序列化时忽略多余的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        // 设置日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 在序列化和反序列化中支持多态
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 将驼峰命名法转换为下划线风格
        objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE);
        // 反序列化时允许未知属性
//        objectMapper.configure(DeserializationFeature.ACCEPT_UNKNOWN_PROPERTIES, true);
        // 美化输出的Json字符串
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * 将Java对象序列化为JSON格式的字符串
     *
     * @param obj Java对象
     * @return JSON格式的字符串
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将JSON格式的字符串反序列化为Java对象
     *
     * @param json  JSON格式的字符串
     * @param clazz Java对象的Class
     * @return Java对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将JSON格式的字符串反序列化为List类型的Java对象
     *
     * @param json JSON格式的字符串
     * @param typeRef TypeReference对象，指定泛型类型
     * @return List类型的Java对象
     */
    public static <T> List<T> fromJsonToList(String json, TypeReference<List<T>> typeRef) {
        try {
            return objectMapper.readValue(json, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
