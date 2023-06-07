package com.java.dp._03behavioralPatterns._02strategy;

// 策略模式, 比较器, 只有一个方法 相当于是函数式接口
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);

    // 1.8之后增加了默认方法
    default void print(T t) {
        System.out.println(t.toString());
    }

}
