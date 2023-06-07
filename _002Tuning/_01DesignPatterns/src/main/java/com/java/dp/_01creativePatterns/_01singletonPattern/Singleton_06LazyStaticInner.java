package com.java.dp._01creativePatterns._01singletonPattern;

/**
 * 静态内部类方式: 推荐
 * JVM保证单例, 类只会被加载一次
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 */
public class Singleton_06LazyStaticInner {

    private Singleton_06LazyStaticInner() {
    }

    // 定义一个静态内部类, 防止随着类加载而加载, 在调用的时候才会加载
    private static class Mgr07Holder {
        private final static Singleton_06LazyStaticInner INSTANCE = new Singleton_06LazyStaticInner();
    }

    public static Singleton_06LazyStaticInner getInstance() {
        return Mgr07Holder.INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(()->{
                System.out.println(Singleton_06LazyStaticInner.getInstance().hashCode());
            }).start();
        }
    }


}
