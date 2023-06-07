package com.java.dp._02structuralPatterns._04decorator;

// 抽象的装饰对象
public abstract class AbstractDecorator extends Decorator {

    // 需要装饰的对象
    private Decorator decorator;

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public String show() {
        return this.decorator.show();
    }
}
