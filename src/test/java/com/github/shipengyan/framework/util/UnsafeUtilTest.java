package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2017-12-27 8:46
 * @since 1.0
 */
@Slf4j
public class UnsafeUtilTest {

    @Test
    public void run16() throws Exception {
        Unsafe unsafe = UnsafeUtil.getUnsafe();

        log.debug("{}", unsafe.addressSize());
    }
}
