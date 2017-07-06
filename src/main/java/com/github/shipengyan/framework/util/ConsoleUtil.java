package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 控制台相关
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-05 22:16
 * @since 1.0
 */
@Slf4j
public class ConsoleUtil {

    /**
     * 等待键盘输入
     */
    public static final void await() {
        try {
            System.in.read();
        } catch (IOException e) {
            log.error("sytem.in.read()", e);
        }
    }

}
