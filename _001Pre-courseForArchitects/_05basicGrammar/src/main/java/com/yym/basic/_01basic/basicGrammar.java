package com.yym.basic._01basic;

import java.util.HashMap;

/**
 * @Description: 基础语法复习
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-08-24 15:11
 */
public class basicGrammar {
    public static void main(String[] args) {

    }

    private void test1() {
        byte a = 127;
        byte b = 127;
        // += 会将byte类型转换为int类型
//        b = a +b;
        b += a;
    }

    private void test2() {
        Object o = new Object();
        // object的equals是用 == 号进行判断
        o.equals("1");
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.equals("1");
    }
}
