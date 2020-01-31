package com.springboot.demo.StaticProxy;

/**
 * @Description
 * @Author wanglei
 * @Date 2019/12/23
 */
public class CalculatorProxy implements Calculator {

    private Calculator target;

    public CalculatorProxy (Calculator calculator) {
        this.target = calculator;
    }

    public CalculatorProxy() {}

    @Override
    public int add(int a, int b) {
        System.out.println("this add method start");
        int result = target.add(a, b);
        System.out.println("this add method end");
        return result;
    }

    @Override
    public int subtract(int a, int b) {
        System.out.println("this subtract start");
        int result = target.subtract(a, b);
        System.out.println("this subtract end");
        return result;
    }
}
