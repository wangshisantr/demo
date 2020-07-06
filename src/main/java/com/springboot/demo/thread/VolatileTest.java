package com.springboot.demo.thread;

/**
 * @author wanglei
 * @description volatile变量自增测试
 * @date 2020/7/1
 */
public class VolatileTest {

    private static volatile int num = 0;
    private static final int COUNT = 20;


    private  static void  increase () {
        num ++;
    }
    public static void main(String[] args) {

        Thread[] threads = new Thread[COUNT];
        // 开启20个线程
        for (int i = 0; i < COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();

        }
        // 等待所有累加线程结束
        if (Thread.activeCount() > 1) {
            Thread.yield();
        }
        // 最后的结果不到20000，说明在volatile不能完全解决多线程并发问题
        // 每个线程都有线程私有的工作内存，主存线程共享
        // 每个线程的工作内存都保存一份变量的副本
        System.out.println(num);
    }
}
