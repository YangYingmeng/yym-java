package com.java.dp._03behavioralPatterns._04facade.module;

// 子系统1
public class Select {
    public void select() {
        System.out.println("挑东西");
    }

    // 子系统内部
    public void innerSelect() {
    }

    // 单例
    private static class InnerSelect {
        private final static Select select = new Select();
    }

    public static Select getSelect() {
        return InnerSelect.select;
    }
}
