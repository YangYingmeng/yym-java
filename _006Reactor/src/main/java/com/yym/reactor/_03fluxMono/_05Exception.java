package com.yym.reactor._03fluxMono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

public class _05Exception {
    public static void main(String[] args) {
        test3();
    }

    // 1. 定义错误以及打印, 异常是终止信号
    private static void test1() {
        Flux.range(1,6)
                .map(i -> 10/(i-3))
                .map(i -> i*i)
                .subscribe(System.out::println, System.out::println);   // 第二个参数定义了对错误的处理方式
    }

    // 2. 捕获异常, 并提供一个缺省值
    private static void test2() {
        Flux.range(1,6)
                .map(i -> 10/(i-3))
                .onErrorReturn(0)   // 发生异常时, 用0代替
                .map(i -> i*i)
                .subscribe(System.out::println, System.out::println);   // 第二个参数定义了对错误的处理方式
    }

    // 3. 捕获并执行一个异常处理方法或计算一个候补值来顶替
    private static void test3() {
        Flux.range(1,6)
                .map(i -> 10/(i-3))
                .onErrorResume(e -> Mono.just(new Random().nextInt(6)))   // 捕获异常用一个替补Mono代替
                .map(i -> i*i)
                .subscribe(System.out::println, System.out::println);   // 第二个参数定义了对错误的处理方式
    }

    // 4. 捕获，并再包装为某一个业务相关的异常，然后再抛出业务异常 onErrorMap

    // 5. 捕获，记录错误日志再继续抛出 doOnError

}
