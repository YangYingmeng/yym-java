package com.java.dp._03behavioralPatterns._04facade.module;

// 子系统1
public class Order {
    // 提供给外部系统的方法
    public void order() {
        System.out.println("下单");
    }

    // 子系统内部
    public void innerOrder() {
    }

    // 单例
    private static class OrderInner {
        private final static Order order = new Order();
    }
    public static Order getOrder(){
        return OrderInner.order;
    }
}
