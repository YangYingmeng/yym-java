package com.java.dp._02structuralPatterns._01adapter;

/*
    适配器模式:
        将一个A接口转换为B接口, 使得新的对象符合B接口的规范
*   编写适配器的步骤
*       1. 实现目标接口, 这里是Runnable
*       2. 内部持有一个待转换接口的引用，这里是通过字段持有Callable接口；
*       3. 在目标接口的实现方法内部，调用待转换接口的方法。
* */
public class AdapterTest {
    public static void main(String[] args) {
        // thread是Runnable接口, task实现的是callable接口, 无法使用, 需要适配器转换
        Task task = new Task(132L);
        // 用的是Runnable接口, 但实际传的Task是Callable接口
        Thread thread = new Thread(new RunnableAdapter(task));
        thread.start();
    }
}
