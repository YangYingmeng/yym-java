package com.yym.juc._01BasicOfThread._02CreateThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 创建线程的第三个方法 实现callable接口
 *                  1. 实现callable接口 实现业务逻辑 拿到返回参数 相当于创建任务类
 *                  2. 将任务类交给 FutureTask
 *                      2.1 FutureTask相当于桥梁  FutureTask实现了 RunnableFuture 接口
 *                      2.2 RunnableFuture 接口 继承了 Runnable, Future 接口
 *                      2.3 Runnable 接口是 Thread类的 Target 参数 最终调用run()方法 执行线程
 *                      2.4 Future接口 是对异步任务的交互接口 提供取消任务 拿到返回参数等方法
 *                  3. 将 FutureTask 交给 Thread 执行
 *              由于 Thread 类的构造函数 只提供了 Runnable 接口, 所以需要  RunnableFuture 桥梁进行连接
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 11:45
 */
public class _04CreateThreadByImplementCallable {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;


    private static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class ReturnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(getCurThreadName() + " 线程运行开始.");
            Thread.sleep(1000);
            for (int i = 0; i < COMPUTE_TIMES; i++) {
                int j = i * 10000;
            }
            long used = System.currentTimeMillis() -
                    startTime;
            System.out.println(getCurThreadName() + " 线程运行结束.");
            return used;
        }
    }

    public static void main(String[] args) {
        ReturnableTask returnableTask = new ReturnableTask();
        FutureTask<Long> futureTask = new FutureTask<>(returnableTask);
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getCurThreadName() + "让子弹飞一会");
        System.out.println(getCurThreadName() + "做一点自己的事情");
        for (int i = 0; i < COMPUTE_TIMES / 2; i++) {
            int j = i * 10000;
        }
        System.out.println("获取并发执行的任务: " + getCurThreadName());
        try {
            System.out.println(thread.getName()+"线程占用时间："
                    + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(getCurThreadName() + " 运行结束.");
    }
}
