package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-28 9:53
 * @since 1.0
 */
@Slf4j
public class StringUtilTest {
    @Test
    public void run15() throws Exception {
        String str = "s1 s2   s3";


        log.debug("{}", Arrays.asList(str.split(" ")));
        log.debug("{}", Arrays.asList(StringUtil.splitBySpace(str)));

    }
}
