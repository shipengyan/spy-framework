package com.github.shipengyan.framework.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/4/1
 * @since 1.0
 */
@Slf4j
public class JSONTest {

    @Test
    public void stringToMapTest() throws Exception {
        String str = "{\"abc\":1}";

        Map<String, Object> map = JSON.parseObject(str, Map.class);
        log.debug("{}", map);

    }
}
