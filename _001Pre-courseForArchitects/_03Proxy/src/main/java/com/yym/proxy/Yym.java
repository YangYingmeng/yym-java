package com.yym.proxy;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-18 22:51
 */
public class Yym implements Person {
    @Override
    public String sing(String name) {
        System.out.println("唱歌: " + name);
        return "载歌";
    }

    @Override
    public String dance(String name) {
        System.out.println("跳舞: " + name);
        return "载舞";
    }
}
