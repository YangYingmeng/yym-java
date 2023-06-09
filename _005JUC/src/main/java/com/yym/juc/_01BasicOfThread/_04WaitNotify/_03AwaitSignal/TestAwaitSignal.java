package com.yym.juc._01BasicOfThread._04WaitNotify._03AwaitSignal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestAwaitSignal {

    private static void normal() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " -- come in --");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "-- 被唤醒 --");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "-- 发出唤醒通知 --");
        }, "t2").start();
    }


    /*
     *   异常状况演示
     *       1. await 和 signal 必须在 lock.lock {} lock.unlock 中使用, 且需要成对
     *       2. await 和 signal 顺序无法变更, 先等待再唤醒, 否则报错
     * */
    private static void inNormal() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            //lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " -- come in --");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "-- 被唤醒 --");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            //lock.lock();
            try {
                condition.signal();
            } finally {
                //lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "-- 发出唤醒通知 --");
        }, "t2").start();
    }

    public static void main(String[] args) throws InterruptedException {
        inNormal();
    }
}
