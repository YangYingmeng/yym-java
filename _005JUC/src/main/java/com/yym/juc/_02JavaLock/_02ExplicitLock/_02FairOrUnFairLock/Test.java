package com.yym.juc._02JavaLock._02ExplicitLock._02FairOrUnFairLock;

/**
 * @Description: 显示锁(指实现了 Lock接口 JUC下的锁)
 *         1. 公平锁与非公平锁  抢夺资源的概率是否一致
 *              1. 公平锁:
 *                 1.1 定义: 多个线程按照申请锁的顺序来获取资源
 *                 1.2 实现: ReentrantLock fairLock = new ReentrantLock(true);
 *              2. 非公平锁:
 *                  2.1 定义: 多个线程不按照申请锁的顺序来获取资源, 容易造成部分线程饥饿的现象
 *                  2.2 实现: synchronized     ReentrantLock unfairLock = new ReentrantLock(false);
 *              3. 为什么非公平锁是默认状态?
 *                  3.1 线程从恢复挂起到获取资源有一定时间差, 按照顺序执行无法充分利用cpu
 *                  3.2 采用公平锁线程切换的开销大
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-25 11:42
 */
public class Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.unFairSale();
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.unFairSale();
            }
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.unFairSale();
            }
        }, "c").start();
    }
}
