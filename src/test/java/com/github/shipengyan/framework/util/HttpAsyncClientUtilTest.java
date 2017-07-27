package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-27 13:49
 * @since 1.0
 */
@Slf4j
public class HttpAsyncClientUtilTest {

    @Test
    public void run16() throws Exception {
        String content = HttpAsyncClientUtil.get("http://www.baidu.com/");
        log.debug("content={}", content);
    }
}
