package com.github.shipengyan.framework.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.junit.Test;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/4/1
 * @since 1.0
 */
@Slf4j
public class StringSubstitutorTest {

    @Test
    public void run16() throws Exception {
        String userHome = StringSubstitutor.replaceSystemProperties("${user.home}");

        log.debug(userHome);


        // 默认值
        String str = " this is ${undefined.number:-1234567890}";

        String ret = StringSubstitutor.replace(str, Maps.newHashMap());

        log.debug("{}", ret);
    }
}
