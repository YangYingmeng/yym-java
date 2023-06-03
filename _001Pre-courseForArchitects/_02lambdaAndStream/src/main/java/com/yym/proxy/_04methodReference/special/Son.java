package com.yym.proxy._04methodReference.special;

public class Son extends Father{
    @Override
    public void instructorSelf() {
        System.out.println("hello, 我是子类");
    }

    // 定义一个方法, 函数式接口作为参数
    public void method(Greet greet) {
        greet.greet();
    }

    public void superInstruct() {
        // 调用method方法
//        method(() -> {
//            // 构造器
//            Father father = new Father();
//            father.instructorSelf();
//            // 父类
//            super.instructorSelf();
//        });
        // 该类引用方法格式  super::方法名, this也是如此
        method(super::instructorSelf);
    }

    public void thisInstruct() {
        method(this::instructorSelf);
    }
}
