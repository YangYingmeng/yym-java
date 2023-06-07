package com.java.dp._01creativePatterns._01singletonPattern;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 * Class.forName("")
 * （话说你不用的，你装载它干啥）
 * ***
 */
public class Singleton_01Hungry {
    // 类加载时, 便会实例化
    // 1. 私有化, 出了该类new不出来
    private static final Singleton_01Hungry instance = new Singleton_01Hungry();

    private Singleton_01Hungry() {};
    // 2. 提供一个方法获得实例
    public static Singleton_01Hungry getInstance() {
        return instance;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Singleton_01Hungry m1 = Singleton_01Hungry.getInstance();
        Singleton_01Hungry m2 = Singleton_01Hungry.getInstance();
        System.out.println(m1 == m2);
    }
}
