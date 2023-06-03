package com.yym.proxy._04methodReference.special;

public class _1FatherMethodReference {
    public static void main(String[] args) {
        Son son = new Son();
        son.thisInstruct();
        son.superInstruct();
    }
}
