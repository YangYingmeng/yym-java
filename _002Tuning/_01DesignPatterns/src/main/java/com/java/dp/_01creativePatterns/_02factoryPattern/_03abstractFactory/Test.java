package com.java.dp._01creativePatterns._02factoryPattern._03abstractFactory;

public class Test {
    public static void main(String[] args) {
        // 在定义其它具有相同行为的族时, 直接定义一个工厂, 但其具体方法需要实现
        HumanFactory human = new HumanFactory();
        human.createEat().eat();
        human.createDrink().drink();
        human.createClothe().clothe();
    }
}
