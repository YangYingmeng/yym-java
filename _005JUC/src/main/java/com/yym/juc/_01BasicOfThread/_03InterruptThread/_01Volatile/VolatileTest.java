package com.yym.juc._01BasicOfThread._03InterruptThread._01Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @Description: volatile保证了可见性，t2修改了标志位后能马上被t1看到
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-09 9:05
 */
public class VolatileTest {

    // flag为true时, 停止运行
    static volatile boolean isStop = false;

    public static void main(String[] args) throws InterruptedException {
        // a线程只有自己把自己停掉
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "isStop=true 停止运行");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " hello volatile");
            }
        }, "a").start();
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(() -> {
            isStop = true;
        }, "b").start();
    }
}
