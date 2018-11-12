package com.github.shipengyan.framework.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import redis.clients.jedis.Jedis;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/11/12
 * @since 1.0
 */
@Slf4j
public class BaseRedisTest {

    protected Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("localhost", 6379);
    }
}
