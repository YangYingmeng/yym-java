package com.yym.reactor._03fluxMono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
*   数据流有了之后必须通过订阅的方式 才会处理
* */
public class _02Subscribe {

    public static void main(String[] args) {
        test2();


    }

    // 1. subscribe基本使用, subscribe作用于每一个元素
    private static void test1() {
        Flux.just(1,2,3).subscribe(System.out::print);
        System.out.println();
        Mono.just(1).subscribe(System.out::println);
    }

    // 2. 正确处理和错误处理
    private static void test2() {
        Flux.just(1, 2, 3, 4, 5, 6).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Completed!"));

        Mono.error(new Exception("some error")).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Completed!")
        );

    }

}
