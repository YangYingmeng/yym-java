package com.yym.juc._01BasicOfThread._04WaitNotify._02WaitNotify;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-09 9:15
 */
public class TestWaitNotify {

    // 正常情况下的 notify 和 wait
    private static void normal() throws InterruptedException {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " -- come in --");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "-- 被唤醒 --");
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (o) {
                o.notify();
                System.out.println(Thread.currentThread().getName() + "-- 发出唤醒通知 --");
            }
        }, "t2").start();
    }

    /*
     *   异常状况演示
     *       1. wait 和 notify 必须在 同步块或方法中使用, 且需要成对 synchronized(){中使用, 否则报错}
     *       2. wait 和 notify 顺序无法变更, 先等待再唤醒, 否则报错
     * */
    private static void inNormal() throws InterruptedException {
        Object o = new Object();
        new Thread(() -> {
            //synchronized (o) {
            System.out.println(Thread.currentThread().getName() + " -- come in --");
            try {
                o.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "-- 被唤醒 --");
            //}
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            // synchronized (o) {
            o.notify();
            System.out.println(Thread.currentThread().getName() + "-- 发出唤醒通知 --");
            // }
        }, "t2").start();
    }

    public static void main(String[] args) throws InterruptedException {
        normal();
    }
}
