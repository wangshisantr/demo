package com.springboot.demo.staticproxy;

/**
 * @Description 静态代理接口
 * @Author wanglei
 * @Date 2019/12/23
 */
public interface Calculator {
    /**
     *
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b);

    /**
     *
     * @param a
     * @param b
     * @return
     */
    int subtract(int a, int b);
}
