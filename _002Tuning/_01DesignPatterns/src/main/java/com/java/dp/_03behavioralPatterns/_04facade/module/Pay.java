package com.java.dp._03behavioralPatterns._04facade.module;

// 子系统1
public class Pay {
    public void pay() {
        System.out.println("付款");
    }

    // 子系统内部
    public void innerPay() {
    }
}
