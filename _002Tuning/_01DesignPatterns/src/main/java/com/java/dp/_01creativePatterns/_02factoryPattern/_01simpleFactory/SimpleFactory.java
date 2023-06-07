package com.java.dp._01creativePatterns._02factoryPattern._01simpleFactory;


import com.java.dp._01creativePatterns._02factoryPattern.Car;
import com.java.dp._01creativePatterns._02factoryPattern.Plane;

// 简单工厂, 弊端: 前置功能写死
public class SimpleFactory {

    public Car getCar() {
        // 前置功能
        return new Car();
    }

    public Plane getPlane() {
        // 前置功能
        return new Plane();
    }

}
