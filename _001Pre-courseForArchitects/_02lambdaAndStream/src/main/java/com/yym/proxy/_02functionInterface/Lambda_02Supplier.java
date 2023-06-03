package com.yym.proxy._02functionInterface;

import java.util.function.Supplier;

public class Lambda_02Supplier {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,66};

        int max = getMax(() -> {
            int temp = arr[0];
            for (int i = 1; i < arr.length; i++)
                if (temp <= arr[i])
                    temp = arr[i];
            return temp;
        });
        System.out.println(max);
    }
    // 函数式接口当做参数
    public static int getMax(Supplier<Integer> supplier) {
        return supplier.get();
    }
}
