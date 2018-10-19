package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/10/19
 * @since 1.0
 */
@Slf4j
public class ChineseSTUtilTest {
    @Test
    public void run15() throws Exception {
        String trans = ChineseSTUtil.toTranditional("国");
        String simple = ChineseSTUtil.toSimple(trans);
        log.info("{}->{}", simple, trans);
    }
}
