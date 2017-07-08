package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-08 11:44
 * @since 1.0
 */
@Slf4j
public class NumberUtilTest {

    @Test
    public void run() {
        // double比较
        System.out.println(NumberUtils.compare(2, 1));
        // 返回BigDecimal类型
        BigDecimal bDecimal = NumberUtils.createBigDecimal("123456789");
        System.out.println(bDecimal);
        // 数字判断
        System.out.println(NumberUtils.isDigits("123.123"));
        // 数值判断
        System.out.println(NumberUtils.isCreatable("123.123"));
        // 返回最大值
        System.out.println(NumberUtils.max(new double[]{3.33, 8.88, 1.11}));
    }
}
