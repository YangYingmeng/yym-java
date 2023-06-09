package com.yym.juc._01BasicOfThread._02CreateThread;

import java.util.concurrent.*;

/**
 * @Description: 通过创建线程池获取线程 高并发的场景下 前三种方式会不断地创建 销毁线程 影响性能
 *                  1. 创建线程池
 *                  2. 创建执行的任务(Runnable 或 Callable)
 *                  3. 执行任务 execute(Runnable) 或 submit(Runnable/Callable)
 *                  4. submit(Callable) 可以拿到 Future接口 管理异步执行的任务
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 12:07
 */
public class _05CreateThreadByThreadPool {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    // 创建一个包含三个线程的线程池
    private static ExecutorService pool = Executors.newFixedThreadPool(3);


    private static void sleepMilliSeconds(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // 创建一个 Runnable 任务
    static class DemoThread implements Runnable {
        @Override
        public void run() {
            for (int j = 1; j < MAX_TURN; j++) {
                System.out.println(getCurThreadName() + ", 轮次：" + j);
                sleepMilliSeconds(10);
            }
        }
    }
    // 创建一个 Callable 任务
    static class ReturnableTask implements Callable<Long> {
        //返回并发执行的时间
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(getCurThreadName() + " 线程运行开始.");
            for (int j = 1; j < MAX_TURN; j++) {
                System.out.println(getCurThreadName() + ", 轮次：" + j);
                sleepMilliSeconds(10);
            }
            long used = System.currentTimeMillis() - startTime;
            System.out.println(getCurThreadName() + " 线程运行结束.");
            return used;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        pool.execute(new DemoThread());
        Future<Long> future = pool.submit(new ReturnableTask());
        Long result = (Long) future.get();
        System.out.println("异步执行任务结果: " + result);
        sleepMilliSeconds(Integer.MAX_VALUE);
    }
}
