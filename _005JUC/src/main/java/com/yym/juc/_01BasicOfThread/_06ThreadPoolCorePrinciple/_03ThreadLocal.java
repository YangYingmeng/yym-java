package com.yym.juc._01BasicOfThread._06ThreadPoolCorePrinciple;

import lombok.Data;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: ThreadLocal 原理
 *                  1. 为每个线程提供一个独立的本地副本去解决并发访问的冲突问题
 *                  2. 使用场景
 *                      2.1 线程隔离    为每个线程提供一个本地值, 其它线程不可见
 *                      2.2 跨函数传递数据     同一线程在某些地方设置的值, 在随后的任意地方都可以获取(如传递UID)
 *                  3. ThreadLocal源码分析
 *                      3.1 ThreadLocal.set()
 *                          1. 获取当前线程对象 Thread
 *                          2. 从当前Thread对象中获取 ThreadLocalMap 成员变量
 *                          3. 若当前Thread对象中有 ThreadLocalMap, 则ThreadLocalMap.set(当前ThreadLocal实例, value)
 *                          4. 若不存在ThreadLocalMap, 则创建一个Map, 再ThreadLocalMap.set(当前ThreadLocal实例, value)
 *                      3.2 ThreadLocal.get()
 *                          1. 获取当前线程对象 Thread
 *                          2. 从当前Thread对象中获取 ThreadLocalMap 成员变量
 *                          3. 以当前的ThreadLocal为key, 尝试获得value, value存在返回结果
 *                          4. 若map为null, 则设置当前map的初始值并返回
 *                      3.3 ThreadLocal.remove()
 *                          1. 获取当前线程对象 Thread
 *                          2. 从当前Thread对象中获取 ThreadLocalMap 成员变量
 *                          3. 以当前的ThreadLocal作为key, 删除map对应key的数据
 *                      3.4 initialValue()方法
 *                          直接返回null值, 可以重写该方法
 *                          建议使用 ThreadLocal.withInitial(() -> new Object)方法 创建ThreadLocal
 *                  4. ThreadLocalMap
 *                          建议使用 private static final 修饰, 使用结束时用remove()释放内存, 防止内存泄漏
 *
 *
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 22:31
 */
public class _03ThreadLocal {
    // 定义线程本地变量
    private static final ThreadLocal<ThreadLocalTest> threadLocal = new ThreadLocal<ThreadLocalTest>();

    private static void sleepMilliSeconds(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    public static class ThreadLocalTest {
        // 实例总数
        static final AtomicInteger AMOUNT = new AtomicInteger(0);
        // 对象的编号
        int index = 0;
        // 对象的内容
        int bar = 10;
        public ThreadLocalTest() {
            index = AMOUNT.incrementAndGet();
        }
        @Override
        public String toString() {
            return index + "@Foo{bar=" + bar + '}';
        }

        public static void main(String[] args) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
            for (int i = 0; i < 5; i++) {
                threadPoolExecutor.execute(() -> {
                    if (null == threadLocal.get()) {
                        threadLocal.set(new ThreadLocalTest());
                    }
                    System.out.println("初试的本地值: " + threadLocal.get());
                    // 每个线程执行10次
                    for (int j = 0; j < 10; j++) {
                        ThreadLocalTest threadLocalTest = threadLocal.get();
                        threadLocalTest.setBar(threadLocalTest.getBar() + 1);
                        sleepMilliSeconds(10);
                    }
                    System.out.println("累加10次后的本地值: " + threadLocal.get());
                    // 删除本地线程的值
                    threadLocal.remove();
                });

            }
        }
    }
}
