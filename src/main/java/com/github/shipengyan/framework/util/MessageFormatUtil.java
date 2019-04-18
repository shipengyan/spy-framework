package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

/**
 * 消息格式化
 *
 * @author spy
 * @version 1.0 2019/4/18
 * @since 1.0
 */
@Slf4j
public final class MessageFormatUtil {

    /**
     * 格式化消息
     *
     * @param messagePattern
     * @param args
     * @return
     */
    public static String format(String messagePattern, Object... args) {

        return MessageFormatter.arrayFormat(messagePattern, args).getMessage();
    }
}
