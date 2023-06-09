package com.yym.juc._01BasicOfThread._02CreateThread;

/**
 * @Description: 创建线程的方法  一: 继承Thread类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-20 12:39
 */
public class _02CreateThreadByExtendThread {

    public static final int MAX_TURN = 5;

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

    static int threadNo = 1;

    // 1. 通过继承Thread类创建线程
    public static class Demo extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(getThreadName() + " 轮次: " + i);
            }
            System.out.println(getThreadName() + " - 运行结束");
        }

        public Demo() {
            super("Demo-" + threadNo++);
        }
    }

    public static void createThreadByExtendThread() {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            thread = new Demo();
            thread.start();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(getThreadName() + " -- 运行结束");
    }

    public static void main(String[] args) {
        createThreadByExtendThread();
    }
}
