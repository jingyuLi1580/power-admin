package com.example.powerAdmin.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljy
 * @date
 * @description
 **/
@Slf4j
public class BeanUtil {

    public static <T, V> List<V> map2List(List<T> sourceList, Class<V> targetClass, String... ignoreProperties) {
        List<V> targetList = new ArrayList<>();
        if (sourceList != null && sourceList.size() != 0) {
            for (T node : sourceList) {
                targetList.add(map2obj(node, targetClass, ignoreProperties));
            }
        }
        return targetList;
    }

    public static <T> T map2obj(Object source, Class<T> targetClass, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("类转换错误:{}", e.getMessage(), e);
        }
        return null;
    }
}
