package com.github.shipengyan.framework.util.excel;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-07 14:31
 * @since 1.0
 */
@Slf4j
@Data
@Accessors(chain = true)
public class ExcelConfig {

    Boolean isExcel2003;

    String datePattern;

}
