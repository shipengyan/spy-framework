package com.github.shipengyan.framework.util.id.support;

import com.github.shipengyan.framework.util.id.IdWorker;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于十进制的id生成 更直观
 *
 * @version 1.0 2017-07-07 13:21
 * @since 1.0
 */
@Slf4j
public class IdHexWorker implements IdWorker {
    private static final long startTime = 1464710400L; //定义一个起始时间 2016-6-1 00:00:00

    private static final long workerIdBits     = 2L;
    private static final long dataCenterIdBits = 2L;
    private static final int  maxWorkerId      = 99;
    private static final int  maxDataCenterId  = 99;

    private static final long   sequenceBits      = 4;
    private static final long   workerIdShift     = (long) Math.pow(10, sequenceBits);
    private static final long   dataCenterIdShift = (long) Math.pow(10, sequenceBits + workerIdBits);
    private static final long   timeLeftShift     = (long) Math.pow(10, sequenceBits + workerIdBits + dataCenterIdBits);
    private static final Random r                 = new Random();

    private final long workerId;
    private final long dataCenterId;
    private final long idEpoch;
    private long lastTime = -1L;
    private long sequence = 0;

    public IdHexWorker() {
        this(startTime);
    }

    public IdHexWorker(long idEpoch) {
        this(r.nextInt(maxWorkerId), r.nextInt(maxDataCenterId), 0, idEpoch);
    }

    public IdHexWorker(int workerId, int dataCenterId, long sequence) {
        this(workerId, dataCenterId, sequence, startTime);
    }

    public IdHexWorker(int workerId, int dataCenterId, long sequence, long idEpoch) {
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
        this.idEpoch = idEpoch;

        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalArgumentException("workerId is illegal: " + workerId);
        }
        if (dataCenterId < 0 || dataCenterId > maxDataCenterId) {
            throw new IllegalArgumentException("dataCenterId is illegal: " + dataCenterId);
        }

        if (idEpoch >= timeGen()) {
            throw new IllegalArgumentException("idEpoch is illegal: " + idEpoch);
        }
    }

    public static void main(String[] args) throws Exception {
        IdHexWorker     worker   = new IdHexWorker(1, 1, 0);
        ExecutorService executor = Executors.newFixedThreadPool(8);

        CountDownLatch countDownLatch = new CountDownLatch(1000000);
        Runnable run = () -> {
            System.out.println(worker.nextId());
            countDownLatch.countDown();
        };

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            executor.execute(run);
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - startTime);
        executor.shutdown();
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getTime() {
        return timeGen();
    }

    @Override
    public synchronized long nextId() {
        long time = timeGen();
        if (time < lastTime) {
            throw new IllegalArgumentException("Clock moved backwards.");
        }

        if (lastTime == time) {
            sequence = (sequence + 1) % workerIdShift;
            if (sequence == 0) {
                time = tilNextSecond(lastTime);
            }
        } else {
            sequence = 0;
        }

        lastTime = time;
        long id = ((time - idEpoch) * timeLeftShift) + dataCenterId * dataCenterIdShift
            + workerId * workerIdShift + sequence;
        return id;
    }

    public long getIdTime(long id) {
        return idEpoch + (id / timeLeftShift);
    }

    private long tilNextSecond(long lastTime) {
        long time = timeGen();
        while (time <= lastTime) {
            time = timeGen();
        }

        return time;
    }

    private long timeGen() {
        return System.currentTimeMillis() / 1000;
    }
}
