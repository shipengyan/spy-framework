package com.github.shipengyan.framework.util.serialize;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-17 13:16
 * @since 1.0
 */
public interface SeriablizeUtil {

    <T> byte[] seriablize(T obj);

    <T> T deseriablize(byte[] data, Class<T> clazz);

}
