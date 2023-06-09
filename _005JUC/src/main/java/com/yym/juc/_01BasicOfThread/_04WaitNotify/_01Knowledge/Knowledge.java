package com.yym.juc._01BasicOfThread._04WaitNotify._01Knowledge;

/*
*       三种等待唤醒机制:
*           1. Object中的wait()方法让线程等待，使用Object中的notify()方法唤醒线程 -- synchronized
*                 核心API: Object的wait() 和 notify()
*                 缺点: 1. 需要先获得锁, 必须在锁块(synchronized)中运行
*                      2. 必须得先wait, 再notify, 且顺序不可变
*           2. 使用JUC包中Condition的await()方法让线程等待，使用signal()方法唤醒线程 -- lock
*                核心API: Condition的await() 和 signal()
*                缺点: 1. Condition中的线程等待和唤醒方法，需要先获取锁, 必须在锁块(lock)中运行
*                     2. 必须得先await, 再signal, 且顺序不可变
*           3. LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程
*                定义: 用于创建锁和其他同步类的基本线程阻塞原语
*                核心API: park()方法是阻塞线程,  unpark()方法是解除阻塞线程
*                原理: 每个线程都有一个permit(只有2个值0 或 1), 用permit来做唤醒和阻塞, 类似(0,1)信号量, 不可累加(c++方法)
*                优点:
*                   1.不用在 锁块 中执行
*                   1. park unpark 顺序无所谓, 但unpark需要指定线程
*                   3. 不需要成对使用, 因为 permit不会累计
*                   4. 线程阻塞工具类
 * */
public class Knowledge {

}


