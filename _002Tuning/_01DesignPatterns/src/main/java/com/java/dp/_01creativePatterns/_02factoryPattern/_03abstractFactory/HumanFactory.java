package com.java.dp._01creativePatterns._02factoryPattern._03abstractFactory;

public class HumanFactory extends AbstractFactory{
    @Override
    public AbstractEat createEat() {
        // 当需要前置增强时使用, 工厂方法思想
        System.out.println("前置增强功能  吃饭前需要煮饭");
        return new Rice();
    }

    @Override
    public AbstractDrink createDrink() {
        System.out.println("前置增强功能 喝可乐前需要买可乐");
        return new Coke();
    }

    @Override
    public AbstractClothe createClothe() {
        System.out.println("前置增强功能 穿adidas前需要赚钱");
        return new Adidas();
    }
}
