package com.yym.utils.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程池工具
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 17:02
 */
@Slf4j
public class ThreadUtil {

    public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
        // 若已经关闭则返回
        if (threadPool == null || threadPool.isTerminated()) {
            return;
        }

        try {
            // 请求线程池优雅关闭
            threadPool.shutdown();
            // 等待线程池中的任务完成执行
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 如果超时，立即调用shutdownNow()取消正在执行的任务
                threadPool.shutdownNow();
                // 再次等待线程池中的任务执行完成
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.out.println("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException e) {
            // 捕获异常，重新尝试关闭
            threadPool.shutdownNow();
            // 重新设置中断状态
            Thread.currentThread().interrupt();
        }
    }
}

