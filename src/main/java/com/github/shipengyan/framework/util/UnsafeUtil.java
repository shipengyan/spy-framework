package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2017-12-26 19:28
 * @since 1.0
 */
@Slf4j
public class UnsafeUtil {

    protected static Unsafe u;

    public static Unsafe getUnsafe() {
        return u;
    }

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);

            u = (Unsafe) f.get(null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
