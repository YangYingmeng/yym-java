package com.java.dp._01creativePatterns._03builderPattern;

// 汉堡种类有多种, 但都是用纸包装, 抽象一个父类
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
