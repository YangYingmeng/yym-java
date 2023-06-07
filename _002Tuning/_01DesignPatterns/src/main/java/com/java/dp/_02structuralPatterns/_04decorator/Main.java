package com.java.dp._02structuralPatterns._04decorator;


import com.java.dp._02structuralPatterns._04decorator.clothe.Pants;
import com.java.dp._02structuralPatterns._04decorator.clothe.Shirt;

public class Main {
    public static void main(String[] args) {
        People people = new People("张三");
        Pants pants = new Pants();
        Shirt shirt = new Shirt();
        pants.setDecorator(people);
        shirt.setDecorator(people);
        System.out.println(shirt.show());
    }
}
