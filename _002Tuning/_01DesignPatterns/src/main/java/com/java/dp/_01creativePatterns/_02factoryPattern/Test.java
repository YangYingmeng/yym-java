package com.java.dp._01creativePatterns._02factoryPattern;


import com.java.dp._01creativePatterns._02factoryPattern._01simpleFactory.SimpleFactory;
import com.java.dp._01creativePatterns._02factoryPattern._02factoryMethod.CarFactory;
import com.java.dp._01creativePatterns._02factoryPattern._02factoryMethod.PlaneFactory;

public class Test {
    public static void main(String[] args) {
        // 反复创造类, 代码可用性较低
        Car car = new Car();
        car.go();
        Plane plane = new Plane();
        plane.go();
        // 提取公共方法到父接口, 多态; 依旧需要实现多个父接口
        Moveable car1 = new Car();
        car1.go();
        Moveable plane1 = new Plane();
        plane1.go();
        // 使用静态工厂; go方法的前置功能写死
        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.getCar().go();
        // 使用工厂方法, 每个工厂可以自定义自己的前置功能; 需要写多个类型的工厂
        new CarFactory().create().go();
        new PlaneFactory().create().go();
    }
}
