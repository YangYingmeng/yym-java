package com.yym.tuning._01jvm._04tuning;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 认识常用GC的参数
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-01 21:53
 */
public class _01HelloGC {
    public static void main(String[] args) {
        System.out.println("HelloGC!");
        List list = new LinkedList();
        for(;;) {
            byte[] b = new byte[1024*1024];
            list.add(b);
        }
    }
}
