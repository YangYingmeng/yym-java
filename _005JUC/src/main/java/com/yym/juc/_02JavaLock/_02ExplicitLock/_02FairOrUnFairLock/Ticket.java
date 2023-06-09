package com.yym.juc._02JavaLock._02ExplicitLock._02FairOrUnFairLock;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

/*
*   卖票的类
* */
@Data
public class Ticket {
    private int num  = 50;
    // 非公平锁, 默认
    ReentrantLock unfairLock = new ReentrantLock(false);
    // 公平锁
    ReentrantLock fairLock = new ReentrantLock(true);

    public void unFairSale() {
        unfairLock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第: " + (num--) + " 张票, 还剩下 "+(50-num)+" 张票");
            }
        } finally {
            unfairLock.unlock();
        }
    }

    public void fairSale() {
        fairLock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第: " + (num--) + " 张票, 还剩下 "+(50-num)+" 张票");
            }
        } finally {
            fairLock.unlock();
        }
    }
}
