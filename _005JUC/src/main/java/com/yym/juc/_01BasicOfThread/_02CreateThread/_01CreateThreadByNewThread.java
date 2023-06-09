package com.yym.juc._01BasicOfThread._02CreateThread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 创建空线程
 *                1. 直接new Thread()
 *                2. 用() -> {执行的任务逻辑}
 *                3. .start
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-16 19:55
 */
@Slf4j
public class _01CreateThreadByNewThread {

    public static void createEmptyThread() {
        Thread thread = new Thread();
        log.info("线程名称: " + thread.getName());
        log.info("线程优先级: " + thread.getPriority());
        log.info("线程id: " + thread.getId());
        // 未执行start方法时, 启用的还是main线程
        log.info(Thread.currentThread() + "运行结束");
        thread.start();
    }

    public static void main(String[] args) {
        createEmptyThread();
    }
}
