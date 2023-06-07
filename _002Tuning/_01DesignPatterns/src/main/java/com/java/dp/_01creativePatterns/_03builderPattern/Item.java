package com.java.dp._01creativePatterns._03builderPattern;

// 表示食品种类的条目, 顶级父类
public interface Item {

    String name();
    Packing packing();
    float price();
}
