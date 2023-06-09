package com.yym.reactor._01lambdaAndStream._04methodReference;

/*
* 1. 通过类名引用静态成员方法
*           类已经存在, 静态方法也已经存在, 可以通过类名直接方法引用静态成员方法
* */
public class _1StaticMethodReference {
    public static int method(int number, Calcable calcable) {
        return calcable.calsAbs(number);
    }

    public static void main(String[] args) {
        // lambda作为参数实现的是Calcable中的抽象方法的逻辑
        int method = method(-10, item -> {
            // abs方法的参数和返回值类型和函数式接口的参数和返回值类型数目一致
            return Math.abs(item);
        });

        method(-10, Math::abs);
        System.out.println(method);
    }
}
