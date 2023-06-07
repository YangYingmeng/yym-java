package com.java.dp._01creativePatterns._01singletonPattern;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 */
public class Singleton_02Lazy {
    // 刚开始不初始化
    private static Singleton_02Lazy instance;
    // 构造方法私有化
    private Singleton_02Lazy() {
    }

    public static Singleton_02Lazy getInstance() {
        // 第一次调用getInstance后, 之后的instance已初始化, 不为空, 不会再创建 所以单例
        // 问题1: 2个线程同时进入if时, 会创建2个instance, 不是同一个实例
        if (instance == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton_02Lazy();
        }
        return instance;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            // 开启多个线程 判断对象地址
            new Thread(
                    () -> System.out.println(Singleton_02Lazy.getInstance().hashCode())
            ).start();
        }
    }
}
