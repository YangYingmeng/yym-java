package com.yym.juc._02JavaLock._01InnerLock;

/**
 * @Description:
 *              synchronized: java自带的对象锁
 *                      1. 可以用在方法 代码块上  静态代码块上(此时锁的是Class对象, 是类锁本质是对象锁)
 *                      本质上锁的是对象 为了区分Object对象的监视锁叫做对象锁 Class对象的监视锁叫做类锁
 *                      使用synchronized加锁时, 不用考虑锁的释放问题
 *                      属于悲观锁, 适合写较多的场景
 *              java的对象结构与内置锁
 *                      1. java的对象结构包括: 对象头 对象体 对齐字节
 *                          对象头:
 *                              mark_word: 存放GC标识位 锁状态 偏向锁标识等信息
 *                              class pointer: 存放方法区的的Class地址, 需要知道当前实例是哪个class的实例
 *                              Array Length: 如果对象是一个数组, 才会有该字段
 *                          对象体:
 *                              包含对象的成员变量
 *              java内置锁的分类
 *                     1. 无锁
 *                     2. 偏向锁:
 *                          一段同步代码一直被同一个线程访问, 该线程会自动获取锁, 偏向锁是在mark_word中记录 偏向锁标识 锁标识 记录对应的线程id
 *                          线程在获取锁时, 仅需判断对应的线程id和标识位, 便可以访问临界资源
 *                          主要是消除无竞争状态下的同步原语, 一旦有第二条线程参与锁的竞争 偏向模式结束进入轻量级锁模式
 *                     3. 轻量级锁状态(非阻塞同步锁, 乐观锁):
 *                          若有2个线程在竞争这个锁对象, 锁会由偏向锁升级为轻量级锁; 此时各个线程公平竞争锁资源,
 *                          锁对象的mark_word中记录的线程id, 变为抢占到锁资源的线程id, 然后将锁对象的mark_word中的记录保留一份到线程对象中, 用于锁的释放
 *                          主要减少重量级锁的开销, 重量级锁是操作系统级别的锁, 导致线程在用户态和核心态之间切换
 *                          分类:
 *                              普通自旋锁: 当有线程竞争锁时, 抢锁线程会在原地循环等待,而不会被阻塞
 *                              自适应自旋锁: 空循环的自旋次数并非固定
 *                                              如果抢锁线程之前获得过锁, 认为其下次获取锁的概率会高, 增加自旋时间
 *                                              如果抢锁线程之前未获得锁, 则认为获得锁的概率会低, 减少或取消自旋
 *                          当竞争激烈时, 膨胀为重量级锁
 *                     4. 重量级锁(同步锁):
 *                          重量级锁会让其它申请的线程之间进入阻塞状态, 性能会降低
 *                          JVM中每个对象都会有一个监视器, 监视器和对象一起创建、销毁
 *                          监视器中有三个队列, 队列中存放的是被阻塞的线程, 监视器的owner指针指向的是获得锁的线程
 *                              Cxq: 竞争队列, 所有请求锁的线程 首先被放在竞争队列中
 *                              EntryList: Cxq中可以成为候选资源的线程被移动到EntryList中
 *                              WaitSet:  被监视器的wait()方法阻塞的线程放在该队列中
 *                          具体流程: 抢锁线程首先进入竞争队列中, 然后从竞争队列中选取具有候选资格的候选线程放入EntryList中, 待操作
 *                                   系统调度, 当线程在运行过程中被.wait()方法阻塞, 线程会进入waitSet队列中, 当被唤醒时, 重新进入EntryList
 *                          处于这些队列中的线程都会被阻塞, 也就是说操作系统需要不断的在用户态和核心态之间切换
 *                     5. CAS原理:
 *                          首先会将内存位置的值与预期值(旧值)比较, 匹配再将内存位置的值自动更新为新值返回ture; 不匹配返回false
 *                     6. ABA问题:  线程1从内存中取出一个数值v1, 同时线程2也取到该数值, 线程2先将v1变为v2, 再变为v1, 线程1无感知使用的仍是v1
 *
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-23 16:56
 */
public class SynchronizedDemo {

    // 在方法上使用 synchronized
    public static synchronized void selfPlus() {
        int amount = 0;
        for (int i = 0; i < 10000; i++) {
            System.out.println(amount);
            amount++;
        }

    }

    // 代码块锁
    public static void synCodeBlock() {
        Object o = new Object();
        int amount = 0;
        synchronized (o) {
            for (int i = 0; i < 10000; i++) {
                System.out.println(amount);
                amount++;
            }
        }
    }
    // 静态代码块
    public static void main(String[] args) {
        selfPlus();
    }
}
