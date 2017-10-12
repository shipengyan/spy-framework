package com.github.shipengyan.framework.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回值
 *
 * @author shi.pengyan
 * @version 1.0 2017-10-11 20:15
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class BaseResult extends BaseDomain {

    @JSONField(ordinal = -103)
    private Boolean success = true;

    @JSONField(ordinal = -1020)
    private String errorCode; // 错误编码

    @JSONField(ordinal = -1010)
    private String errorMessage;// 错误信息

    @JSONField(ordinal = -1005)
    private String errorField; // 错误字段

    @JSONField(ordinal = -1000)
    private String errorType; // 错误类别

    @JSONField(ordinal = -990)
    private Object result; // 默认返回结果

    public BaseResult() {
        this(true, null);
    }

    public BaseResult(boolean success, String errorMsg) {
        this.success = success;
        this.errorMessage = errorMsg;
    }

    public BaseResult(Object result) {
        this.success = true;
        this.result = result;
    }

    /**
     * 直接返回true
     *
     * @return BaseResult
     */
    public static BaseResult success() {
        return new BaseResult(true, null);
    }

}
