package com.java.dp._03behavioralPatterns._02strategy;

import java.util.Arrays;

/**
 * writing tests first!
 * extreme programming
 */
public class Main {
    public static void main(String[] args) {
        // 对不同类型进行排序, 写多个sort方法或者使用泛型
        Sorter sorter = new Sorter();
        Dog[] d = {new Dog(1), new Dog(3), new Dog(2)};
        sorter.sort(d, new DogComparator());
        System.out.println(Arrays.toString(d));

        Cat[] c = {new Cat(22, 21), new Cat(21, 22)};
        Sorter<Cat> catSorter = new Sorter<>();
        catSorter.sort(c, (o1, o2) -> {
            if (o1.height - o2.height > 0) return 1;
            else if (o1.height - o2.height < 0) return -1;
            return 0;
        });
        System.out.println(c);
    }
}
