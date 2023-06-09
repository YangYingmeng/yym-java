package com.yym.juc._02JavaLock._02ExplicitLock._04DeadLock;


import java.util.concurrent.TimeUnit;


/**
 * @Description: 显示锁(指实现了 Lock接口 JUC下的锁)
 *         1. 死锁
 *              定义: 2个或2个以上的线程争夺资源导致互相等待的现象
 *              产生的条件:
 *                  1. 互斥条件(资源互斥)
 *                  2. 请求与保持条件(请求其它资源并保留自身已有资源不释放)
 *                  3. 不可剥夺条件(已获得的资源在未使用完之前, 不能强行剥夺)
 *                  4. 循环等待条件(多个线程相互等待循环)
 *              如何排查死锁:
 *                  1. jps -l    找到死锁进程pid(自己的启动类名)
 *                     jstatck pid
 *                  2. jconsole(图形化界面) -> 线程 -> 死锁查看
 *
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-25 11:42
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Object A = new Object();
        Object B = new Object();
        new Thread(() -> {
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + " 自己持有A锁, 希望获得B锁");
                sleep();
                synchronized (B) {
                    System.out.println(Thread.currentThread().getName() + " 成功获得B锁");
                }
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + " 自己持有B锁, 希望获得A锁");
                sleep();
                synchronized (A) {
                    System.out.println(Thread.currentThread().getName() + " 成功获得A锁");
                }
            }
        }, "B").start();
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
