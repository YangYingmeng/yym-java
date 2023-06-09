package com.yym.juc._01BasicOfThread._01Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
*   1. 并发与并行
*       并发: 同一实体微观上交替执行, 宏观上同时执行
*       并行: 同一实体微观和宏观上都同时执行
*   2. 进程 线程 管程
*       进程: 系统中运行的一个应用程序
*       线程: 轻量级进程, 一个进程中有多个线程, 操作系统调度的基本单元; 会共享进程的方法区和堆
*       管程: 监视器, 也就是锁
*   3. 用户线程和守护线程
*       用户线程: 系统的工作线程, 会完成程序需要完成的业务操作
*       守护线程: 为其它线程服务, 在后台完成系统性的工作, 如垃圾回收
* */
@Slf4j
public class Thread_01Base {

    public static void test1() {
        Thread thread = new Thread(() -> System.out.println("start"));
        //private native void start0(); 真正执行的是下面的方法, native是第三方函数库, 调用了操作系统的方法
        thread.start();
    }

    public static void test2() {
        // o为锁 也就是管程
        Object o = new Object();
        new Thread(
                () -> {
                    synchronized (o) {
                    }
                }, "t2"
        ).start();
    }

    public static void test3() {
        // daemon用来判断是否是守护线程的属性
        Thread t1 = new Thread(() -> {
            log.debug("{} 开始运行, 是否是守护线程{}", Thread.currentThread().getName(), Thread.currentThread().isDaemon());
            while (true) {
            }
        }, "t1");
        // 将t1设置为守护线程, 必须在start方法之前设置, 运行中的线程不允许改变线程的种类
        t1.setDaemon(true);
        t1.start();
        //3秒钟后主线程再运行
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        log.debug("main线程执行完");
    }
    public static void main(String[] args) {
        test3();
    }
}
