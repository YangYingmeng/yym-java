package com.yym.reactor._03fluxMono;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
*   操作符
* */
public class _03Operator {
    public static void main(String[] args) throws InterruptedException {
        zip();
    }

    // 映射处理
    private static void map() {
        Flux.range(1,6)
                .map(i -> i*i)
                .subscribe(System.out::println);
    }

    // 将每个元素映射成流, 再组合为一个大的数据流
    private static void flatMap() {
        Flux.just("flux", "molno")
                .flatMap(s -> Flux.fromArray(s.split("\\s*")))
                .subscribe(System.out::println);
    }

    // 对元素进行筛选过滤
    private static void filter() {
        Flux.range(1,6)
                .filter(num -> num%2==0)
                .subscribe(System.out::println);
    }

    // 一个个合并
    private static void zip() throws InterruptedException {
        // 每个线程执行完后, 进行一次倒数
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux.zip(getZipDescFlux(), Flux.interval(Duration.ofMillis(200)))   // 声明一个每200ms发出一个元素的long数据流
                .subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);
    }
    // 将字符串拆分为一个个单词
    private static Flux<String> getZipDescFlux() {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        return Flux.fromArray(desc.split("\\s+"));
    }

}
