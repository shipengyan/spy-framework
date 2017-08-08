package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-08 13:19
 * @since 1.0
 */
@Slf4j
public class AddressUtilTest {

    @Test
    public void run16() throws Exception {
        int PORT = 8080;

        log.debug("host address={}", AddressUtil.getHostAddress());  //spy-PC/192.168.2.7
        log.debug("host ip={}", AddressUtil.getHostIp()); //192.168.2.7
        log.debug("host name={}", AddressUtil.getHostName()); //spy-PC
        log.debug("port {} can be used?={}", PORT, AddressUtil.isAvailablePort(PORT));

    }
}
