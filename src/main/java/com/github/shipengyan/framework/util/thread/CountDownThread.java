package com.github.shipengyan.framework.util.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * Created by yuliang on 2016/6/27.
 */
public class CountDownThread<T> implements Runnable {

    private ExecutorService threadPool;
    private CountDownLatch  currentThread;
    private IExecute<T>     execute;
    private List<T>         list;

    public CountDownThread(ExecutorService threadPool, List<T> list, IExecute<T> execute, CountDownLatch currentThread) {
        this.threadPool = threadPool;
        this.list = list;
        this.execute = execute;
        this.currentThread = currentThread;
    }

    @Override
    public void run() {
        list.add(execute.execute());
        currentThread.countDown();
    }

    public void execute() {
        threadPool.execute(this);
    }


}
