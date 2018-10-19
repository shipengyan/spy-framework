package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/10/19
 * @since 1.0
 */
@Slf4j
public class MaskUtilTest {
    @Test
    public void run15() throws Exception {
        Assert.assertEquals(MaskUtil.mask("17600001234", 3, 4), "123****7890");
    }

    @Test
    public void run22() throws Exception {
        String mask = MaskType.EMAIL.mask("smith1234567@qq.com");
        log.info("{}", mask);

    }
}
