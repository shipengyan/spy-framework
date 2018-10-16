package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串模板替换
 *
 * @author spy
 * @version 1.0 2018/4/1
 * @since 1.0
 */
@Slf4j
public class StrSubstitutorTest {

    @Test
    public void run16() throws Exception {

        String str = StrSubstitutor
            .replaceSystemProperties("You are running with java.version = ${java.version} and os.name = ${os.name}.");

        log.debug("{}", str);

        String template = "a.1=${a.1}";
        Map<String, String> map = new HashMap<>();
        map.put("a.1", "1");


        str = StrSubstitutor.replace(template, map);
        log.debug("{}", str);
    }
}
