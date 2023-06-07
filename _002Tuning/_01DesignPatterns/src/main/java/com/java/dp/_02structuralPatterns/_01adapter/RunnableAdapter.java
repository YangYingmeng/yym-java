package com.java.dp._02structuralPatterns._01adapter;

import java.util.concurrent.Callable;

/*
*   Runnable接口的适配器
* */
public class RunnableAdapter implements Runnable{
    // 引用待转换接口
    private Callable<?> callable;

    public RunnableAdapter(Callable<?> callable) {
        this.callable = callable;
    }

    // 实现指定接口
    @Override
    public void run() {
        try {
            callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
