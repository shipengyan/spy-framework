package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2019/4/18
 * @since 1.0
 */
@Slf4j
public class MessageFormatUtilTest {

    @Test
    public void run16() throws Exception {

        String msg = MessageFormatUtil.format("{},{},{},{}", 6, 5, 4, 3, 2, 1);


        log.info("msg={}", msg);
    }
}
