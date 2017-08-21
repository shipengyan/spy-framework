package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 构建基于weight的barrier控制
 * <p>
 * <pre>
 * 场景：
 *   多个loader模块会进行并行加载，但每个loader的加载数据的进度统一受到weight的调度，只有当前的weight的所有数据都完成后，不同loader中的下一个weight才允许开始
 *
 * 实现：
 * 1. 使用AQS构建了一个基于weight的barrier处理，使用一个state进行控制(代表当前运行<state以下的weight运行)，
 * 2. 多个任务之间通过single(weight)进行协调控制
 * </pre>
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-21 15:14
 * @since 1.0
 */
@Slf4j
public class WeightBarrier {
    private ReentrantLock lock      = new ReentrantLock();
    private Condition     condition = lock.newCondition();
    private volatile long threshold;

    public WeightBarrier() {
        this(Long.MAX_VALUE);
    }

    public WeightBarrier(long weight) {
        this.threshold = weight;
    }

    /**
     * 阻塞等待weight允许执行
     * <p>
     * <pre>
     * 阻塞返回条件：
     *  1. 中断事件
     *  2. 其他线程single()的weight > 当前阻塞等待的weight
     * </pre>
     *
     * @throws InterruptedException
     */
    public void await(long weight) throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (isPermit(weight) == false) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞等待当前的weight处理,允许设置超时时间
     * <p>
     * <pre>
     * 阻塞返回条件：
     *  1. 中断事件
     *  2. 其他线程single()的weight > 当前阻塞等待的weight
     *  3. 超时
     * </pre>
     *
     * @param timeout
     * @param unit
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public void await(long weight, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
        try {
            lock.lockInterruptibly();
            while (isPermit(weight) == false) {
                condition.await(timeout, unit);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 重新设置weight信息
     *
     * @throws InterruptedException
     */
    public void single(long weight) throws InterruptedException {
        try {
            lock.lockInterruptibly();
            threshold = weight;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public long state() {
        return threshold;
    }

    private boolean isPermit(long state) {
        return state <= state();
    }
}
