package com.yym.proxy._02functionInterface;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Lambda_01Runnable {
    public static void main(String[] args) {

        // 匿名内部类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name);
            }
        }).start();

        // callAble接口
        new Thread(new FutureTask(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " call");
                return "success";
            }
        })
        ).start();

        // lambda实现
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
