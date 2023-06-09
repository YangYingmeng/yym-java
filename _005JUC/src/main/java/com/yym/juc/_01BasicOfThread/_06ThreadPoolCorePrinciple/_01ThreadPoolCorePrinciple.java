package com.yym.juc._01BasicOfThread._06ThreadPoolCorePrinciple;

import com.yym.utils.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 线程池核心原理
 *                  1. Executor: 通过execute()方法提交任务(先提交 后执行) 任务类型只能是 Runnable
 *                  2. ExecutorService: 通过submit()方法提交任务(先提交 后执行) 任务类型可以是 Runnable或Callable, 最终还是调用execute()方法
 *                  3. AbstractExecutorService: 为ExecutorService中的接口提供默认实现
 *                  4. ThreadPoolExecutor: 线程池, 继承于AbstractExecutorService抽象类
 *                  5. ScheduledExecutorService: 一个可以完成“延时”和“周期性”任务的调度线程池接口
 *                  6. ScheduledThreadPoolExecutor: ScheduledExecutorService的实现
 *                  7. Executors: 静态工厂类, 通过静态工厂方法返回ExecutorService、ScheduledExecutorService等线程池示例对象
 *                          7.1 newSingleThreadExecutor
 *                                  单线程化线程池, 只有一个线程, 任务按照提交次序，一个任务一个任务地逐个执行的场景
 *                          7.2 newFixedThreadPool
 *                                  创建固定线程数量的线程池, 适用于长期执行一个任务, 但是内部使用无界阻塞队列, 任务过多会消耗服务器资源
 *                          7.3 newCachedThreadPool
 *                                  可缓存线程池 可灵活回收空闲线程, 不会限制线程的数量, 当所有线程都在忙时 新任务会重新分配一个线程处理
 *                          7.4 newScheduledThreadPool
 *                                  可调度线程池 提供 延时 和 周期性 调度的线程池, 可以隔一段时间重复执行任务
 *                  8. 一般 Executors 提供的静态工厂被禁止使用 线程池的参数得自己定义
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 15:20
 */
public class _01ThreadPoolCorePrinciple {
    public static final int SLEEP_GAP = 500;

    private static void sleepMilliSeconds(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // 1. 测试 newSingleThreadExecutor
    public static class SingleThreadExecutorTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;

        public SingleThreadExecutorTask() {
            taskName = "task-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 任务：" + taskName + " doing");
            // 线程睡眠一会
            sleepMilliSeconds(SLEEP_GAP);
            System.out.println(taskName + " 运行结束.");
        }
        // 调用线程池测试
        static void executePoolTask() {
            ExecutorService pool = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 5; i++) {
                // 执行任务时 也会提交
                pool.execute(new SingleThreadExecutorTask());
                pool.submit(new SingleThreadExecutorTask());
            }
            sleepMilliSeconds(1000);
            //优雅关闭线程池
            ThreadUtil.shutdownThreadPoolGracefully(pool);
        }

    }

    // 2. 测试 newFixedThreadPool
    public static class FixedThreadPoolTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;

        public FixedThreadPoolTask() {
            this.taskName = "threadTask-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "  任务：" + taskName + " doing");
            // 线程睡眠一会
            sleepMilliSeconds(SLEEP_GAP);
            System.out.println(taskName + " 运行结束.");
        }

        public static void executePoolTask() {
            ExecutorService pool = Executors.newFixedThreadPool(3);
            for (int i = 0; i < 5; i++) {
                pool.execute(new FixedThreadPoolTask());
                pool.submit(new FixedThreadPoolTask());
            }
            pool.shutdown();
        }
    }


    // 3. 测试 newScheduledThreadPool
    public static class CachedThreadPoolTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;

        public CachedThreadPoolTask() {
            this.taskName = "threadTask-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "  任务：" + taskName + " doing");
            // 线程睡眠一会
            sleepMilliSeconds(SLEEP_GAP);
            System.out.println(taskName + " 运行结束.");
        }

        public static void executePoolTask() {
            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < 5; i++) {
                pool.execute(new FixedThreadPoolTask());
                pool.submit(new FixedThreadPoolTask());
            }
            pool.shutdown();
        }
    }

    // 4. 测试 newScheduledThreadPool
    public static class ScheduledThreadPoolTask implements Runnable {
        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;

        public ScheduledThreadPoolTask() {
            this.taskName = "threadTask-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "  任务：" + taskName + " doing");
            // 线程睡眠一会
            sleepMilliSeconds(SLEEP_GAP);
            System.out.println(taskName + " 运行结束.");
        }

        public static void executePoolTask() {
            ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
            for (int i = 0; i < 2; i++) {
                // 0: 首次执行任务的延迟时间 500: 执行任务的间隔时间
                pool.scheduleAtFixedRate(new FixedThreadPoolTask(), 0, 50000, TimeUnit.MILLISECONDS);
            }
            sleepMilliSeconds(500000000);
            pool.shutdown();
        }
    }

    public static void main(String[] args) {
//         SingleThreadExecutorTask.executePoolTask();
//        FixedThreadPoolTask.executePoolTask();
//        CachedThreadPoolTask.executePoolTask();
         ScheduledThreadPoolTask.executePoolTask();
    }
}
