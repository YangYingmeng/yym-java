package com.yym.juc._01BasicOfThread._06ThreadPoolCorePrinciple;

/**
 * @Description: 标准的创建线程池方法
 *                  静态工厂提供的4中线程池获取方法 最终都是用ThreadPoolExecutor构造方法 生成
 *
 *                  构造方法的核心参数:
 *                      1. int corePoolSize 核心线程池数量(即使空闲也不会被回收)
 *                      2. int maximumPoolSize 线程最大空闲数
 *                      3. long keepAliveTime, TimeUnit unit 线程最大空闲时长以及时间单位
 *                      4. BlockingQueue<Runnable> workQueue 任务的排队队列
 *                              当一个线程从一个空的阻塞队列中获取任务时, 该线程会被阻塞 直至阻塞队列有新任务进入
 *                              4.1 ArrayBlockingQueue 数组实现的阻塞队列, 必须设置队列容量啊
 *                              4.2 LinkedBlockingQueue 链表实现的阻塞队列, 可以设置队列容量也可以不设置(建议设置)
 *                              4.3 PriorityBlockingQueue 具有优先级的无界队列
 *                              4.4 DelayQueue 无界阻塞延迟队列, 队列中每个元素都有过期时间, 只有过期的元素才会出队
 *                              4.5 SynchronousQueue 同步队列
 *                      5. ThreadFactory threadFactory 新线程的生产方式
 *                      6. RejectedExecutionHandler handler 饱和时的拒绝策略
 *                              有界队列满时, 提交的任务会被拒绝
 *                              6.1 AbortPolicy: 拒绝策略(默认策略), 会抛出异常
 *                              6.2 DiscardPolicy: 丢弃策略, 直接丢弃, 不会有异常
 *                              6.3 DiscardOldestPolicy: 丢弃最老任务策略
 *                              6.4 CallerRunsPolicy: 调用者执行策略(新任务添加时, 提交任务的线程自己执行任务, 不会使用线程池中的任务)
 *                              6.5 自定义策略 实现RejectedExecutionHandler, 做日志记录 或者 入库
 *
 *                 执行流程:
 *                      有新任务时, 若核心线程池中有空闲线程, 则用核心线程执行任务
 *  *                   若核心线程数已满, 则任务放入阻塞队列
 *  *                   若阻塞队列已满, 且工作的线程数<maximumPoolSize, 则创建新的线程执行任务
 *  *                   若工作线程数 > maximumPoolSize, 则触发饱和拒绝策略
 *
 *                  调度器的钩子方法:
 *                      beforeExecute() afterExecute() terminated() 分别在任务执行前后 和 线程终止时调用
 *
 *
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-21 16:23
 */
public class _02CreateThreadPoolByStandard {
}
