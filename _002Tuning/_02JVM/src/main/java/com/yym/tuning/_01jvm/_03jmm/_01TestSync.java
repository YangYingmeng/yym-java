package com.yym.tuning._01jvm._03jmm;

/**
 * @Description: 测试: 看其生成的字节码 有MONITORENTER  MONITOREXIT
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-01 21:26
 */
public class _01TestSync {

    synchronized void m() {

    }

    void n() {
        synchronized (this) {

        }
    }

    public static void main(String[] args) {

    }
}
