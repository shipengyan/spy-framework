package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-17 10:37
 * @since 1.0
 */
@Slf4j
public class SFTPUtilTest {

    private SFTPUtil sftpUtil;

    @Before
    public void run16() throws Exception {
        sftpUtil = new SFTPUtil();

        sftpUtil.connect("nginx.cd121.cc", "root", System.getProperty("password"));
    }

    @Test
    public void run22() throws Exception {
        List<String> files = sftpUtil.listFileInDir("/codi/software");

        files.stream().forEach(System.out::println);
    }

    @After
    public void run27() throws Exception {
        sftpUtil.disconnect();
    }

}
