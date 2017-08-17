package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-17 9:55
 * @since 1.0
 */
@Slf4j
public class FTPUtilTest {

    private FTPUtil ftpUtil;

    @Before
    public void setUp() throws IOException {
        ftpUtil = new FTPUtil("120.55.85.150", "root", System.getProperty("password"), true);
        ftpUtil.connect();

    }


    @Test
    public void run16() throws Exception {


        List<String> files = ftpUtil.getFileNameList("/codi/software");
        log.debug("{}", files);
    }


    @After
    public void destroy() throws IOException {
        ftpUtil.close();
    }
}
