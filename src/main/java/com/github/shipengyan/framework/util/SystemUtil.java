package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统工具
 *
 * @author spy
 * @version 1.0 2017-11-08 8:31
 * @since 1.0
 */
@Slf4j
public final class SystemUtil {

    /**
     * 获取可用CPU核数
     *
     * @return
     */
    public static int getProcessorCount() {
        return Runtime.getRuntime().availableProcessors();
    }
}
