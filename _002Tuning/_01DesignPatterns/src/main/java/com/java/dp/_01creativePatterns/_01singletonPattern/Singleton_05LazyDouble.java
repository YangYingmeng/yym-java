package com.java.dp._01creativePatterns._01singletonPattern;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 双重锁解决
 */
public class Singleton_05LazyDouble {
    // 刚开始不初始化, 不加volatile可能会造成Singleton_05LazyDouble为null, 只分配了内存地址未初始化玩
    private static volatile Singleton_05LazyDouble instance;
    // 构造方法私有化
    private Singleton_05LazyDouble() {
    }

    // 加锁解决线程安全问题, 但效率会降低, 可以将锁的位置缩小
    public static Singleton_05LazyDouble getInstance() {
        // 第一次调用getInstance后, 之后的instance已初始化, 不为空, 不会再创建 所以单例
        if (instance == null) {
            synchronized (Singleton_05LazyDouble.class) {
                if (instance == null) { // 再次检测
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton_05LazyDouble();
                }
            }
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
                    () -> System.out.println(Singleton_05LazyDouble.getInstance().hashCode())
            ).start();
        }
    }
}

