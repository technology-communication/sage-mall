package com.dls.web;

import java.util.concurrent.*;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/23 11:14 上午
 * @desc 线程池测试
 * @link https://blog.csdn.net/lipeng_bigdata/article/details/51232266
 */
public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                5,
                TimeUnit.SECONDS,
                queue,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        Runnable runnable = () -> {
            System.out.println("-------");
            System.out.println(Thread.currentThread().getName());
        };
        Runnable runnable2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----2---");
            System.out.println(Thread.currentThread().getName());
        };
        Callable<Integer> callable = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return 1 + 5;
        };
        threadPoolExecutor.execute(runnable);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(runnable2);
            System.out.println(i + "次size:" + queue.size());
        }
        Future<Integer> future = threadPoolExecutor.submit(callable);
        System.out.println(future.get());
        // shutdown()进入待关闭状态（SHUTDOWN）：此状态下，线程池不再接受新的任务，
        // 继续处理阻塞队列中的任务。当阻塞队列中的任务为空，且工作线程数为0的时候，进入整理（TIDYING）状态。
        threadPoolExecutor.shutdown();
        // 停止状态（STOP）：此状态下，线程池不接受新任务，也不处理阻塞队列中的任务，
        // 反而会尝试结束执行中的任务。当工作线程数为0时，进入整理（TIDYING)状态。
        // threadPoolExecutor.shutdownNow();
    }
}
