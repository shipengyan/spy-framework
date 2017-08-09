package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-09 10:17
 * @since 1.0
 */
@Slf4j
public class PDFSignUtilTest {

    // 证书密码
    private static final String cert_pwd = "shipengyan";

    // adobe 中创建的pfx
    private static final String cert_path = "D:\\gitlab\\00my\\spy-framework\\src\\test\\resources\\shipengyan.pfx";

    @Test
    public void signTest() throws Exception {

        PDFSignUtil.sign("c:/test.pdf", "c:/test_sign.pdf", cert_path, cert_pwd, "change..", "hangzhou city");
    }
}
