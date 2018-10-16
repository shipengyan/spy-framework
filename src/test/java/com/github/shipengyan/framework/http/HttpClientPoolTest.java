package com.github.shipengyan.framework.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/10/16
 * @since 1.0
 */
@Slf4j
public class HttpClientPoolTest {
    @Test
    public void getTest() throws Exception {

        String url = "http://www.baidu.com/";

        String resp = HttpClientPoolUtil.get(url);

        log.info("resp={}", resp);
    }


    @Test
    public void postTest() throws Exception {
        String url = "http://www.baidu.com/";

        String resp = HttpClientPoolUtil.post(url);

        log.info("resp={}", resp);
    }
}
