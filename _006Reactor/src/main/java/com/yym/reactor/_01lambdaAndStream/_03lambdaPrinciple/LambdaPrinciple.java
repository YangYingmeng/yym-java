package com.yym.reactor._01lambdaAndStream._03lambdaPrinciple;

import java.util.Arrays;
import java.util.List;

/*
*   反编译: java -jar cfr-0.145.jar LambdaPrinciple.class --decodelambdas false
* */
public class LambdaPrinciple {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        list.forEach(item -> System.out.println(item));
        //LambdaMetafactory
    }
}



// 反编译结果
/*public class LambdaPrinciple {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        list.forEach((Consumer<String>)LambdaMetafactory.metafactory(
            null, null, null,
            (Ljava/lang/Object;)V,
            lambda$main$0(java.lang.String ),
            (Ljava/lang/String;)V)());
    }

    private static void lambda$main$0(String item) {
        System.out.println(item);
    }
}*/
