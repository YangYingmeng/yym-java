package com.java.dp._01creativePatterns._02factoryPattern._02factoryMethod;


import com.java.dp._01creativePatterns._02factoryPattern.Moveable;
import com.java.dp._01creativePatterns._02factoryPattern.Plane;

public class PlaneFactory {
    public Moveable create() {
        System.out.println("plane 的前置方法");
        return new Plane();
    }
}
