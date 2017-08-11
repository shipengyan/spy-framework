package com.github.shipengyan.framework.util.excel;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-07 11:00
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class ExcelSheet<T> {
    private String              sheetName;
    private Map<String, String> headers;
    private Collection<T>       dataset;
}
