package com.github.shipengyan.framework.util.excel;

import lombok.extern.slf4j.Slf4j;

/**
 * Excel 上下文
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-07 13:45
 * @since 1.0
 */
@Slf4j
public class ExcelContext {

    private static ThreadLocal<ExcelConfig> ctx;

    public static ExcelConfig get() {
        if (ctx == null) {
            ctx = new ThreadLocal<ExcelConfig>() {
                @Override
                protected ExcelConfig initialValue() {
                    return new ExcelConfig();
                }
            };
        }
        return ctx.get();
    }

    public static void clear() {
        if (ctx != null) {
            ctx.remove();
        }
    }

}
