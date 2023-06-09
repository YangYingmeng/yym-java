package com.yym.reactor._01lambdaAndStream._04methodReference;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
