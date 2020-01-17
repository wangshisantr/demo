package com.springboot.demo.collection;


import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author lei
 * @Date: 2020/1/7
 * @Description:
 */
public class TestMap {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>(10);
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        // 遍历map
        // 方法一：拿到所有键值对象集合
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        System.out.println();
        // 方法二：拿到所有key
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            System.out.println("key:" + key + " value:" + value);
        }
    }
}
