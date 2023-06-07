package com.java.dp._01creativePatterns._03builderPattern;

// 饮料有多种, 都是用瓶子包装, 抽象一个父类
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();

}
