package com.java.dp._02structuralPatterns._04decorator.clothe;

import com.java.dp._02structuralPatterns._04decorator.AbstractDecorator;

public class Pants extends AbstractDecorator {
    @Override
    public String show() {
        return super.show() + " 裤子 ";
    }
}
