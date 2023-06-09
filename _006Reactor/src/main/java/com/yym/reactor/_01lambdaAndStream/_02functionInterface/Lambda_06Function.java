package com.yym.reactor._01lambdaAndStream._02functionInterface;

import java.util.function.Function;

public class Lambda_06Function {
    static void func(Function<String, Integer> one,  Function<Integer, Integer> two) {
        // andThen: apply(apply(two))  方法二的返回参数必须是方法一的入参
        Integer num = one.andThen(two).apply("10");
        System.out.println(num + 20);
    }

    public static void main(String[] args) {
        // lambda表达式调用
        func(param1 -> Integer.parseInt(param1) + 10, param2 -> param2*=10);
    }

}
