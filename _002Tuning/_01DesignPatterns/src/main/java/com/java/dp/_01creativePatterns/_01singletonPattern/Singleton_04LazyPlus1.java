package com.java.dp._01creativePatterns._01singletonPattern;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 */
public class Singleton_04LazyPlus1 {
    // 刚开始不初始化
    private static Singleton_04LazyPlus1 instance;
    // 构造方法私有化
    private Singleton_04LazyPlus1() {
    }

    // 加锁解决线程安全问题, 但效率会降低, 可以将锁的位置缩小
    public static Singleton_04LazyPlus1 getInstance() {
        // 第一次调用getInstance后, 之后的instance已初始化, 不为空, 不会再创建 所以单例
        // 问题1: 2个线程同时进入if时, 会创建2个instance, 不是同一个实例
        // 此处加锁无用, 1 2线程, 1执行完if判断后, 2先执行完释放锁, 1继续执行
        if (instance == null) {
            synchronized (Singleton_04LazyPlus1.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Singleton_04LazyPlus1();
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
                    () -> System.out.println(Singleton_04LazyPlus1.getInstance().hashCode())
            ).start();
        }
    }
}
