package com.yym.reactor._01lambdaAndStream._02functionInterface;

import java.util.function.Consumer;

public class Lambda_03Consumer {
    public static void main(String[] args) {
      //  consume(s -> System.out.println(s));
        consumerString(s -> System.out.println(s.toUpperCase()),
                s -> System.out.println(s.toLowerCase())
                );
    }

    // 函数式接口作为参数, 接收一个字符串待消费
    public static void consume(Consumer<String> consumer) {
        consumer.accept("consumer");
    }

    // consumer接口的默认方法
    public static void consumerString(Consumer<String> fir, Consumer<String> sec) {
        // 2个消费者都需要消费字符串
        fir.andThen(sec).accept("andThen");
    }
}
