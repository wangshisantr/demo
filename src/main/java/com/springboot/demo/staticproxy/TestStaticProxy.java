package com.springboot.demo.staticproxy;

/**
 * @Description
 * @Author wanglei
 * @Date 2019/12/23
 */
public class TestStaticProxy {


    public static void main(String[] args) {
        Calculator calculator = new CalculatorProxy(new CalculatorImpl());
        calculator.add(1,2);
        calculator.subtract(2,3);
    }
}
