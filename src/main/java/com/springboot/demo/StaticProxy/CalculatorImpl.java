package com.springboot.demo.StaticProxy;

/**
 * @Description
 * @Author wanglei
 * @Date 2019/12/23
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return  a - b;
    }
}
