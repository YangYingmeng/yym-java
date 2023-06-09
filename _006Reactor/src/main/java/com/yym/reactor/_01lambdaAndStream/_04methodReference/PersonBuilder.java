package com.yym.reactor._01lambdaAndStream._04methodReference;

@FunctionalInterface
public interface PersonBuilder {
    Person builder(String name, String age);
}
