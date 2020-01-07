package com.springboot.demo.reflex;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 测试反射
 *
 * @author wanglei
 * @date 2019/11/30
 */
public class TestReflex {
    public static void main(String[] args) throws Exception {

        // 获取类加载器
        ClassLoader classLoader = TestReflex.class.getClassLoader();
        // 获取配置文件的信息，application.properties要在resource目录下
        InputStream stream = classLoader.getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(stream);
        String className = properties.getProperty("class.name");
        String methodName = properties.getProperty("method.name");
        // 代码不变，调用任何对象的任何方法
        Class clazz = Class.forName(className);
        Object instance = clazz.newInstance();
        Method eat = clazz.getDeclaredMethod(methodName);
        eat.invoke(instance);
    }
}
