package com.java.dp._01creativePatterns._01singletonPattern;

/**
 * 不仅可以解决线程同步，还可以防止反序列化。
 * ***
 */
public enum Singleton_07LazyEnum {

    INSTANCE;

    public void m() {}

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(()->{
                System.out.println(Singleton_07LazyEnum.INSTANCE.hashCode());
            }).start();
        }
    }

}
