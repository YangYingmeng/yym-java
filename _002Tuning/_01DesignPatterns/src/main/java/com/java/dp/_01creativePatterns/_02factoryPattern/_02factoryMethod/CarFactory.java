package com.java.dp._01creativePatterns._02factoryPattern._02factoryMethod;


import com.java.dp._01creativePatterns._02factoryPattern.Car;
import com.java.dp._01creativePatterns._02factoryPattern.Moveable;

// 定义一个Car工厂
public class CarFactory {
    public Moveable create() {
        System.out.println("前置方法扩展");
        return new Car();
    }
}
