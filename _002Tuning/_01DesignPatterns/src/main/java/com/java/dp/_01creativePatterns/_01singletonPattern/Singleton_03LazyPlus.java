package com.java.dp._01creativePatterns._01singletonPattern;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来效率下降
 */
public class Singleton_03LazyPlus {
    // 刚开始不初始化
    private static Singleton_03LazyPlus instance;
    // 构造方法私有化
    private Singleton_03LazyPlus() {
    }

    // 加锁解决线程安全问题, 但效率会降低, 可以将锁的位置缩小
    public synchronized static Singleton_03LazyPlus getInstance() {
        // 第一次调用getInstance后, 之后的instance已初始化, 不为空, 不会再创建 所以单例
        // 问题1: 2个线程同时进入if时, 会创建2个instance, 不是同一个实例
        if (instance == null) {
            synchronized (Singleton_03LazyPlus.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Singleton_03LazyPlus();
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
                    () -> System.out.println(Singleton_03LazyPlus.getInstance().hashCode())
            ).start();
        }
    }
}
