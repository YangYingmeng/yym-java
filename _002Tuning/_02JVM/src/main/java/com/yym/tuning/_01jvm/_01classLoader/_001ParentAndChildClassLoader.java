package com.yym.tuning._01jvm._01classLoader;

/**
 * @Description: 类加载器由 Customer ClassLoader  ->  Application ClassLoader  -> Extension ClassLoader
 *                  -> BootStrap ClassLoader
 *               其中   BootStrap ClassLoader, 非Java对象, 返回为null
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-29 14:49
 */
public class _001ParentAndChildClassLoader {
    public static void main(String[] args) {
        System.out.println(_001ParentAndChildClassLoader.class.getClassLoader());
        System.out.println(_001ParentAndChildClassLoader.class.getClassLoader().getClass().getClassLoader());
        System.out.println(_001ParentAndChildClassLoader.class.getClassLoader().getParent());
        System.out.println(_001ParentAndChildClassLoader.class.getClassLoader().getParent().getParent());
    }
}
