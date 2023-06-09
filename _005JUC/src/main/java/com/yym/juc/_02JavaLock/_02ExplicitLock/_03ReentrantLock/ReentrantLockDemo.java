package com.yym.juc._02JavaLock._02ExplicitLock._03ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 显示锁(指实现了 Lock接口 JUC下的锁)
 *         1. 可重入锁  递归锁
 *              定义: 同一个线程在外层方法获取锁, 再进入该线程的内层方法会自动获取锁(前提锁的是同一个对象), 不会因为之前没释放而阻塞
 *              实现: ReentrantLock(显示锁, 加几次锁就要释放几次锁)   synchronized(隐士锁, 不受程代码控制)
 *              优点: 一定程度避免死锁
 *              原理: 每一个对象都有一个objectMonitor.hpp, 当线程进锁的时候monitorExit的字段会增加1
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-25 11:42
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {

        implicitLockDemo();
    }

    //  可重入锁避免了死锁, 同步代码块
    // 隐式锁, 不用自己释放
    private static void implicitLockDemo() {
        final Object object = new Object();
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " --- 外层调用 ---");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + " --- 中层调用 ---");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + " --- 内层调用 ---");
                    }
                }
            }
        }, "t1").start();
    }

    // 加一次锁必须释放一次锁, 否则第二个线程始终无法获取到锁, 导致一直在等待
    // 显示锁必须自己处理 加锁 释放锁的问题
    private static void explicitLockDemo() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-- 外层调用lock --");
                lock.lock();
                try {
                    System.out.println("-- 中层调用lock --");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "a").start();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("-- 外层调用lock --");
            } finally {
                lock.unlock();
            }
        }, "b").start();
    }
}
