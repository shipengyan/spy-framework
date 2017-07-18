package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 13:56
 * @since 1.0
 */
@Slf4j
public class MimeUtilTest {

    @Test
    public void run() throws IOException {
        println("c:/test.txt");
        println("c:/superman-console.png");
        println("c:/a.txt");
        println("c:/cityData.json");
    }

    private void println(String filePath) throws IOException {
        log.debug(MimeUtil.getContentType(filePath));
    }
}
