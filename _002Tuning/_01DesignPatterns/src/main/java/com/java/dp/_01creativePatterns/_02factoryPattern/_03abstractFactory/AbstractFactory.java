package com.java.dp._01creativePatterns._02factoryPattern._03abstractFactory;

// 抽象工厂用来定义一个族
// 比如: eat clothe drink为一个族, 人吃什么 穿什么 喝什么; 狗吃什么 穿什么 喝什么
// 并且它管理具体族工厂, 这些族工厂又可以对原有的方法进行扩展
// 具体族工厂是在eat方法之前做些前置增强处理
public abstract class AbstractFactory {
    public abstract AbstractEat createEat();
    public abstract AbstractDrink createDrink();
    public abstract AbstractClothe createClothe();
}
