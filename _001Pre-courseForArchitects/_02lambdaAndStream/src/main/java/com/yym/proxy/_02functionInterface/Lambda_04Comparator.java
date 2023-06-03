package com.yym.proxy._02functionInterface;

import java.util.Arrays;
import java.util.Comparator;

public class Lambda_04Comparator {

    public static void main(String[] args) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        String str[] = {"123", "1", "23"};
        Arrays.sort(str, comparator);
        System.out.println(Arrays.toString(str));
        Arrays.sort(str, (o1, o2) -> o1.length() - o2.length());

    }
}
