package com.springboot.demo.java8;

/**
 * @author wanglei
 * @description java8 lambda表达式
 * @date 2020/6/15
 */
public class TestLambda {
    // lambda表达式，又称为必包，是java8发布重要的新特性
    // lambda允许把函数作为一个方法的参数
    // lambda把代码变得更加简洁
    public static void main(String[] args) {
        TestLambda tester = new TestLambda();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);
        // 不用新特性
        GreetingService g4 = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println("dsf");
            }
        };

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}
