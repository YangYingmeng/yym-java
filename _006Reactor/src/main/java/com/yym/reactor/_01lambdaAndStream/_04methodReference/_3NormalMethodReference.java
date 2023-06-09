package com.yym.reactor._01lambdaAndStream._04methodReference;

public class _3NormalMethodReference {

    static void print(String str, Print print) {
        print.print(str + " +");
    }
    public static void main(String[] args) {
        print("normal", System.out::println);
    }
}
