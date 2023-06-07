package com.yym.tuning._01jvm._02Initializing;

/**
 * @Description: 演示初始化案例
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-31 14:46
 */
public class _001ClassLoadingProcedure {
    public static void main(String[] args) {
        System.out.println(T.count);
    }
}

class T {
    // T在被加载时, 静态资源初始化, T: null; count:0
    // new T() count++; count = 1;
    // 在初始化的过程中count 被赋初始值 count = 2
    // 如果下面2行代码调换位置, 同理
    public static T t = new T(); // null
    public static int count = 2; //0

    //private int m = 8;

    private T() {
        count ++;
        //System.out.println("--" + count);
    }
}
