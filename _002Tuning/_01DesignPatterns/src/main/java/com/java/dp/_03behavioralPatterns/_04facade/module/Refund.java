package com.java.dp._03behavioralPatterns._04facade.module;


public class Refund {
    public void refund() {
        System.out.println("退款");
    }

    // 子系统内部
    public void innerRefund() {
    }
}
