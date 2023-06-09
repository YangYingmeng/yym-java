package com.yym.juc._01BasicOfThread._03InterruptThread._02AtomicBoolean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: 通过原子类定义线程中断标识位 中断线程运行
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-09 9:05
 */
public class AtomicBooleanTest {

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "isStop=true 停止运行");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " hello atomicBoolean");

            }
        }, "a").start();
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(() -> {
            atomicBoolean.set(true);
        }, "b").start();
    }
}
