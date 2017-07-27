package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-27 13:29
 * @since 1.0
 */
@Slf4j
public class HttpClientUtilTest {


    @Test
    public void getTest() throws Exception {
        String response = HttpClientUtil.get("http://www.baidu.com/");
        log.debug("response={}", response);
    }

    @Test
    public void postTest() throws Exception {
        String response = HttpClientUtil.post("http://www.baidu.com/");

        log.debug("response={}", response);
    }
}
