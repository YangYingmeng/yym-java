package com.yym.tuning._01jvm._01classLoader;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-29 15:05
 */
public class _002LoadClassByHand {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = _002LoadClassByHand.class.getClassLoader().loadClass("com.yym.tuning._01jvm._01classLoader._001ParentAndChildClassLoader");
        System.out.println(clazz.getName());

    }
}
