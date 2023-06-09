package com.yym.reactor._01lambdaAndStream._02functionInterface;

import java.util.function.Predicate;

public class Lambda_05Predicate {
    static void andOrMethod(Predicate<String> one, Predicate<String> two) {
        boolean and = one.and(two).test("hello");
        System.out.println("and: " + and);
        boolean or = one.or(two).test("hello");
        System.out.println("or: " + or);
    }

    static void neg(Predicate<String> predicate) {
        // 取反
        boolean neg = predicate.negate().test("hello");
        System.out.println("取反: " + neg);
    }

    public static void main(String[] args) {
        andOrMethod(s1 -> s1.contains("he"), s2 -> s2.contains("llo"));
        neg(s -> s.contains("he"));
    }
}
