package com.yym.reactor._01lambdaAndStream._04methodReference;

public class _2StructMethodReference {

    static void printPerson(String name, String age, PersonBuilder builder) {
        System.out.println(builder.builder(name, age));
    }

    public static void main(String[] args) {
        printPerson("test", "18", Person::new);
    }
}
