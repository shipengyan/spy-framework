package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-11 17:52
 * @since 1.0
 */
@Slf4j
public class VersionUtilTest {

    @Test
    public void run() {
        log.debug("result={}", VersionUtil.compare("1.0.0", null));
    }
}
