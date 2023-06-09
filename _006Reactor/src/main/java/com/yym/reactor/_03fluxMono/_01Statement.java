package com.yym.reactor._03fluxMono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
    响应式编程主要有三种信号:
        1. 元素值
        2. 错误信号
        3. 完成信号
*   Flux和Mono的声明, Flux可以有1到多个元素, Mono只能有1个元素
*       1. Flux/Mono.just方法声明
*       2. 基于数组 集合 Stream 的方式声明
* */
public class _01Statement {
    public static void main(String[] args) {
        // 1. just的方式声明
        Flux.just(1,2,3);
        Mono.just(1);
        // 2. 基于数组 集合 Stream 的方式声明
        Integer[] array = new Integer[]{1,2,3,4,5,6};
        Flux.fromArray(array);
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);

    }
}
