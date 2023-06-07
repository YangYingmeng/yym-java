package com.java.dp._03behavioralPatterns._04facade;


import com.java.dp._03behavioralPatterns._04facade.facade.Facade;

public class Client {
    public static void main(String[] args) {
        new Facade().buy();
        new Facade().returnGoods();
    }
}
