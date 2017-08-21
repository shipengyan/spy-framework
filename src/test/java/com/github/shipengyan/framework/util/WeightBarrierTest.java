package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-21 15:15
 * @since 1.0
 */
@Slf4j
public class WeightBarrierTest {

    @Test
    public void test_simple() throws InterruptedException {
        final WeightBarrier barrier = new WeightBarrier(10);

        barrier.await(10);// 可以成功通过

        final CountDownLatch count    = new CountDownLatch(1);
        ExecutorService      executor = Executors.newCachedThreadPool();

        executor.submit(new Callable() {

            public Object call() throws Exception {
                Thread.sleep(1000);
                barrier.single(11);
                count.countDown();
                return null;
            }
        });

        barrier.await(11);// 会被阻塞
        count.await();
        executor.shutdown();

    }

    @Test
    public void test_cocurrent() throws InterruptedException {
        final WeightBarrier barrier = new WeightBarrier(-1);


        final CountDownLatch count    = new CountDownLatch(10);
        ExecutorService      executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final long index = i;
            executor.submit(new Callable() {

                public Object call() throws Exception {
                    barrier.await(index);
                    count.countDown();
                    return null;
                }
            });
        }
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            barrier.single(i);
        }
        count.await();
        executor.shutdown();

    }
}
