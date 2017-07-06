package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-06 21:02
 * @since 1.0
 */
@Slf4j
public class KidUtilTest {

    @Test
    public void shortUuidTest() {
        log.debug(KidUtil.generateShortUuid());
    }
}
