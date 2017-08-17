package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-17 9:11
 * @since 1.0
 */
@Slf4j
public class QRCodeUtilTest {

    @Test
    public void run16() throws Exception {
        String FILE_QR = "c:/test_qr_code.png";
        QRCodeUtil.encode(FILE_QR, "spy-framework");

        log.debug("create qr code suc");

        log.debug("decode qr img, content is [{}]", QRCodeUtil.decode(FILE_QR));


    }
}
