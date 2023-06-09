package com.yym.juc._01BasicOfThread._02CreateThread;

/**
 * @Description: 实现Runnable类, 但是创建的并非线程类, 而是target类, 需要当做参数放入Thread中
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 11:22
 */
public class _03CreateThreadByImplementRunnable {

    public static final int MAX_TURN = 5;
    public static String getThreadName() {
        return Thread.currentThread().getName();
    }
    static int threadNo = 1;


    // 2. 通过实现Runnable接口创建线程
    static class RunTarget implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(getThreadName() + " 轮次: " + i);
            }
            System.out.println(getThreadName() + " - 运行结束");
        }
    }

    public static void createThreadByImplementsRunnable() {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            thread = new Thread(new RunTarget());
            thread.start();
        }
    }

    // 优雅实现Runnable接口
    public static void createThreadByImplementsRunnable1() {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            thread = new Thread(() -> {
                for (int j = 0; j < MAX_TURN; j++) {
                    System.out.println(getThreadName() + " 轮次: " + j);
                }
                System.out.println(getThreadName() + " - 运行结束");
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        createThreadByImplementsRunnable();
    }
}
