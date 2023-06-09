package com.yym.juc._02JavaLock._02ExplicitLock._01PessimisticOptimisticLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 显示锁(指实现了 Lock接口 JUC下的锁)
 *         1. 悲观锁与乐观锁
 *              1. 悲观锁:
 *                 1.1 定义: 认为自己在使用数据的时候一定有别的线程来抢锁, 所以在获取数据的时候会先加锁, 确保数据不会被修改
 *                 1.2 实现: synchronized 和 lock 行锁 表锁 都是悲观锁
 *                 1.3 适用场景: 适合写较多的场景
 *              2. 乐观锁:
 *                  2.1 定义: 认为自己在使用数据的时候不会有别的线程修改数据或资源, 所以不会添加锁
 *                  2.2 实现: 一般通过无锁编程实现 如CAS实现的自旋锁
 *                  2.3 判断规则: 版本号机制Version  CAS算法, Java原子类中的递增操作就是通过CAS自旋生成的
 *                  2.4 使用场景: 读多的场景
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-25 11:42
 */
public class OptimisticLock {

    // 基于CAS机制实现的乐观锁(自旋锁)
    public class SpinLock implements Lock {
        AtomicReference<Thread> owner = new AtomicReference<>();

        // 抢占锁
        @Override
        public void lock() {
            Thread thread = Thread.currentThread();
            // 自旋
            while (owner.compareAndSet(null, thread)) {
                // do nothing 让出当前剩余的cpu时间片
                Thread.yield();
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        // 释放锁
        @Override
        public void unlock() {
            Thread thread = Thread.currentThread();
            if (thread == owner.get()) {
                // 设置拥有者为null
                owner.set(null);
            }
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

    public static void main(String[] args) {

    }

}
