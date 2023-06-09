package com.yym.reactor._03fluxMono;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
*   调度器: 负责线程管理和任务调度
*
* */
public class _04Scheduled {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(() -> getStringSync())    // 声明一个Callable的Mono
                .subscribeOn(Schedulers.elastic())  // 将任务调度到Schedulers中内置的弹性线程池, 弹性线程池会为Callable的任务分配一个单独的线程执行
                .subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);
    }

    // 同步获取字符串
    private static String getStringSync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, Reactor!";
    }

}
