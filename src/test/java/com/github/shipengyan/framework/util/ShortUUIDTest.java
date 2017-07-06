package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-06 21:24
 * @since 1.0
 */
@Slf4j
public class ShortUUIDTest {

    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            log.debug(ShortUUID.generate());
        }
    }
}
