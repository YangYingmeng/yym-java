package com.yym.juc._01BasicOfThread._04WaitNotify._04LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

    // 1. 不用在 锁块 中执行    2. 先后顺序可以改变
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " -- come in --");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "-- 被唤醒 --");
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "-- 发出唤醒通知 --");
        }, "t2").start();
    }
}
