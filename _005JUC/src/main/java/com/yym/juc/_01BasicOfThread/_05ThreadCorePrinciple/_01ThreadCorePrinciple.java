package com.yym.juc._01BasicOfThread._05ThreadCorePrinciple;

/**
 * @Description: 线程池核心原理
 *                  1. 线程池的优先级 通过 priority 属性设置. 默认是5, [1,10] 设置的越高只是相对机会越大 并不能确保执行的有序性
 *                  2. 线程的生命周期 通过 threadStatus 保存 Thread.State getState() 该方法可以获取
 *                          NEW: 创建成功 但没有调用start()方法启动的线程
 *                          RUNNABLE: 操作系统的 就绪态 和 运行态 2合一
 *                          TERMINATED: 线程任务执行完或者抛出异常 进入终止状态
 *                          TIMED_WAITING: 限时等待状态 sleep wait join等都会事线程进入该状态
 *                  3. 可以使用JStack工具查看当前线程快照 排查问题 一般需要导出三次快照进行排查
 *                          jstack <pid> // pid表示当前线程id, 用jps命令查看
 *              线程池的基本操作
 *                  1. 名称的设置和获取 未命名的线程系统会自动分配 或者通过构造函数进行自命名
 *                  2. 线程的sleep()操作
 *                          让正在执行的线程休眠, cpu去执行其它任务(运行态 -> 限时阻塞态)
 *                  3. 线程中断方法
 *                          不推荐 stop()方法, 强制中断 如果线程正在持有锁或者操作数据库 强制中断可能导致锁无法释放或数据库数据异常
 *                          interrupt(): 并非中断线程 是给线程设置一个中断状态
 *                              1. 如果线程处于阻塞状态, 调用interrupt() 会终止阻塞状态 抛出InterruptedException
 *                              2. 如果线程处于运行状态, 调用interrupt() 会将线程的中断标记设为true, 配合isInterrupted(), 再执行退出动作
 *                  4. 线程合并方法 join() A线程需要将B线程的执行流程合并到自身的执行流程中, A线程会等待B线程执行完
 *                  5. 线程让步操作 yield() 让目前正在执行的线程放弃当前的执行, 使cpu执行其它线程 (运行态 -> 就绪态)
 *                              操作系统的调度可能会从就绪态的线程中选中刚刚执行完yield()方法的线程
 *                  6. 守护线程 daemon 为用户线程提供服务(如GC线程)
 *                              可以通过setDaemon()方法将某个线程设置为守护线程
 *                              用户线程结束 -> JVM进程结束 JVM进程终止 -> 守护线程终止
 *
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 12:27
 */
public class _01ThreadCorePrinciple {
    private static final int MAX_TURN = 50;
    public static final int SLEEP_GAP = 5000;

    private static void sleepMilliSeconds(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }

    static class RunTarget implements Runnable {
        @Override
        public void run() {
            for (int turn = 0; turn < MAX_TURN; turn++) {
                sleepMilliSeconds(500);//线程睡眠
                System.out.println(getThreadName() + "  线程执行轮次:" + turn);
            }
        }
    }
    // 测试 sleep
    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i < MAX_TURN; i++) {
                    System.out.println(getName() + ", 睡眠轮次：" + i);
                    Thread.sleep(SLEEP_GAP);
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + " 发生异常被中断.");
            }
            System.out.println(getName() + " 运行结束.");
        }
    }
    // 手动为线程命名
    public static void setNameForThread() {
        RunTarget runTarget = new RunTarget();
        new Thread(runTarget).start();
        new Thread(runTarget, "手动命名-1").start();

    }

    public static void testInterrupt() {
        SleepThread thread1 = new SleepThread();
        thread1.start();
        SleepThread thread2 = new SleepThread();
        thread2.start();
        sleepMilliSeconds(2000);
        thread1.interrupt();
        sleepMilliSeconds(5000);
        thread2.interrupt();
        sleepMilliSeconds(1000);
        System.out.println("结束运行");
    }

    // 测试 isInterrupt()
    public static void testIsInterrupt() {
        int i = 1;
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("线程启动");
                    if (isInterrupted()) {
                        System.out.println("线程结束");
                        return;
                    }
                }
            }
        };
        thread.start();
        sleepMilliSeconds(2000);
        thread.interrupt();
        sleepMilliSeconds(2000);
        thread.interrupt();
    }

    public static void main(String[] args) {
        testIsInterrupt();
    }
}
