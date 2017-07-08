package com.github.shipengyan.framework.util.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-08 11:10
 * @since 1.0
 */
@Slf4j
public class MD5Test {

    @Test
    public void run() {
        Assert.assertEquals(MD5.getStringMD5("123456"), "e10adc3949ba59abbe56e057f20f883e");
    }
}
