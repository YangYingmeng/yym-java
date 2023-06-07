package com.java.dp._02structuralPatterns._04decorator;

// 具体类
public class People extends Decorator {
    String name;

    public People(String name) {
        this.name = name;
    }

    @Override
    public String show() {
        return name + "穿";
    }
}
