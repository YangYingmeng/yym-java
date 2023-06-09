package com.yym.juc._01BasicOfThread._03InterruptThread._03threadAPI;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 *      中断机制:
 *          首先线程应该由自己自行停止, 而不是其它线程让它停止,Java提供了一种用于停止线程的协商机制——中断,只是改变标识
 *      三大API:
 *          1. public void interrupt()
 *               实例方法interrupt()仅仅是设置线程(处于正常运行)的中断状态为true，发起一个协商而不会立刻停止线程
 *          2. public static boolean interrupted()
 *               静态方法，Thread.interrupted();判断线程是否被中断，并清除当前中断状态
 *                   1 返回当前线程的中断状态
 *                   2 将当前线程的中断状态设为false（这个方法有点不好理解，因为连续调用两次的结果可能不一样)
 *          3. public boolean isInterrupted()
 *               实例方法，判断当前线程是否被中断（通过检查中断标志位） *
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-09 9:07
 */
public class ThreadAPITest {

    public static void main(String[] args) throws InterruptedException {

        //testInterrupt();
        //testInterrupt2();
        testInterrupted();
    }

    // interrupt isInterrupted的基本使用
    private static void baseUser() throws InterruptedException {
        Thread a = new Thread(() -> {
            while (true) {
                // 判断 中断标识位是否被修改
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "isStop=true 停止运行");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " hello interrupt");
            }
        }, "a");
        a.start();
        TimeUnit.MILLISECONDS.sleep(20);
        new Thread(a::interrupt).start();
    }

    // 当前线程的中断标识为true，是不是线程就立刻停止? 不会, 线程会继续运行
    private static void testInterrupt() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println(" --- " + i);
            }
            System.out.println("t1线程调用interrupt()后的中断标识位02: " + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        // 默认中断标识位: false
        System.out.println("t1线程默认标识位: "+t1.isInterrupted()); //false
        TimeUnit.MILLISECONDS.sleep(2);
        t1.interrupt(); // 将中断标识设置为 true
        System.out.println("t1线程调用interrupt()后的中断标识位01: " + t1.isInterrupted()); //true
        TimeUnit.MILLISECONDS.sleep(10000);
        // 当线程运行结束, 中断不活动的线程不会产生任何影响，线程结束后应该是自动变为了false
        System.out.println("t1线程调用interrupt()后的中断标识位03: " + t1.isInterrupted());
    }

    /*
     *     问题:
     *       如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），在别的线程中调用当前线程对象的interrupt方法，
     *       那么线程将立即退出被阻塞状态（中断状态将被清除），并抛出一个InterruptedException异常, 程序无法终止
     *     解决方案:
     *           在捕获异常的地方, 再次调用interrupt方法, 可以终止程序
     *     执行步骤
     *           1 中断标志位 默认是false
     *           2 t2 ----->t1发出了中断协商，t2调用t1.interrupt()，中断标志位true
     *           3 中断标志位true，正常情况下，程序停止
     *           4 中断标志位true，异常情况下，InterruptedException，将会把中断状态清除，并且将收到InterruptedException。中断标志位false导致无限循环。
     *           5 在catch块中，需要再次给中断标志位设置为true，2次调用停止
     * */
    private static void testInterrupt2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "isStop=true 停止运行");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // 此时可以终止程序
                    e.printStackTrace();
                }
                System.out.println("-- hello --");
            }
        }, "t1");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        new Thread(thread::interrupt).start();
    }

    /*
     *   静态方法Thread.interrupted()，谈谈你的理解
     *   静态方法，Thread.interrupted();判断线程是否被中断，并清除当前中断状态
     *           1 返回当前线程的中断状态
     *           2 将当前线程的中断状态设为false
     * */
    private static void testInterrupted() {
        System.out.println(Thread.currentThread().getName()+"  isStop: "+Thread.interrupted()); // false
        System.out.println(Thread.currentThread().getName()+"  isStop: "+Thread.interrupted()); // false
        System.out.println("-----1");
        Thread.currentThread().interrupt();//中断标志位设置为true
        System.out.println("-----2");
        // 1. 先返回中断状态 true  2. 再将中断标识清零, 设置为false
        System.out.println(Thread.currentThread().getName()+"  isStop: "+Thread.interrupted()); // true
        System.out.println(Thread.currentThread().getName()+"  isStop: "+Thread.interrupted()); // false
    }
}
