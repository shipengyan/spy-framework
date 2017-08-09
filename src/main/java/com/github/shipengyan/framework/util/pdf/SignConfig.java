package com.github.shipengyan.framework.util.pdf;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-09 10:39
 * @since 1.0
 */
@Slf4j
@Data
@Accessors(chain = true)
public class SignConfig {

    private String  srcFile;
    private String  signedFile;
    private String  certPath;
    private String  certPwd;
    private Boolean showSignature;
    private String  signReason;
    private String  signLocation;
}
