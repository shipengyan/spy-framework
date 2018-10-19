package com.github.shipengyan.framework.exception;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 异常基类
 *
 * @author shi.pengyan
 * @date 2017-06-26 10:38
 */
@Slf4j
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class BaseAppException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * 业务错误
     */
    private static final String ERROR_TYPE_BUSINESS = "BIZ";

    /*编码*/
    private String code;

    /*描述*/
    private String desc;
    /***
     * 默认为业务错误，理论上业务错误不需要报警
     */
    private String errorType = ERROR_TYPE_BUSINESS;

    // 可扩展参数

    public BaseAppException() {
        super();
    }

    public BaseAppException(String code) {
        this.code = code;
    }

    public BaseAppException(String code, String desc) {
        super(code + ":" + desc);
        this.code = code;
        this.desc = desc;
    }

}
