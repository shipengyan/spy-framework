package com.github.shipengyan.framework.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/11/12
 * @since 1.0
 */
@Slf4j
public class RedisDelayingQueueTest extends BaseRedisTest {


    @Test
    public void run17() throws Exception {

        RedisDelayingQueue<String> queue = new RedisDelayingQueue<>(jedis, "my-queue");

        //consumer
        new Thread(() -> {
            queue.loop(msg -> log.info("msg={}", msg));

        }).start();

        //producer
        new Thread(() -> {
            int count = 0;
            while (true) {
                queue.delay("msg" + count++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();


        TimeUnit.MINUTES.sleep(10);
    }
}
