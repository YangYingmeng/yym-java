package com.yym.reactor._03fluxMono;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

/*
*   回压测试
* */
public class _06BackPressure {
    public static void main(String[] args) {
        Flux.range(1,6)     // 快的publisher
                .doOnRequest(n -> System.out.println("request " + n + " values..."))     // 每次request的时候打印request的个数
                .subscribe(new BaseSubscriber<Integer>() {      // 重写BaseSubscriber方法自定义Subscribe
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {     // 订阅时执行的操作
                        System.out.println("Subscribed and make a request...");
                        request(1);     // 订阅时向上游请求一个元素
                    }

                    @Override
                    protected void hookOnNext(Integer value) {      // 定义在每次收到一个元素时的操作
                        try {
                            TimeUnit.SECONDS.sleep(1);      // 睡眠模拟慢subscribe
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");
                        request(1);     //  处理完1个元素后在请求1个
                    }
                });
    }
}
