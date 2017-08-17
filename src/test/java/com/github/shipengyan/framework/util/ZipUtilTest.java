package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-17 11:34
 * @since 1.0
 */
@Slf4j
public class ZipUtilTest {
    File file    = new File("c:/test_sign.pdf");
    File zipFile = new File("c:/a.zip");

    @Test
    public void run15() throws Exception {
        ZipUtil.compress(file, zipFile);
    }

    @Test
    public void run29() throws Exception {
        ZipUtil.decompress(zipFile, "test_sign.pdf", "c:/test/");
    }

}
