package com.yym.juc._02JavaLock._02ExplicitLock._03ReentrantLock;

import lombok.Data;

@Data
public class Reentrant {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " --- m1 ---");
        m2();
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + " --- m2 ---");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + " --- m3 ---");
    }

}
