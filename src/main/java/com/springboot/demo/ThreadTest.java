package com.springboot.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * 多线程
 *
 * @author wanglei
 * @date 2019/10/31
 */
public class ThreadTest {
    /**
     * 创建线程池
     */
    private static Executor executor = new ThreadPoolExecutor(5, 5, 5L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5));

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), threadFactory, new ThreadPoolExecutor.AbortPolicy());
    private static ScheduledExecutorService executorService2 = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    // 人为制造异常
                    // int a = 1 / 0;
                    System.out.println("线程1:" + i);
                }
            }
        });

        executorService.execute(() -> {
            for (int i = 0; i < 100; i++) {
                // int a = 1 / 0;
                System.out.println("线程2:" + i);
            }
        });
    }
    // class th implements Runnable {
    //
    //     @Override
    //     public void run() {
    //         for (int i = 0; i < 100; i++) {
    //             System.out.println("线程1:" + i);
    //         }
    //     }
    // }
}

