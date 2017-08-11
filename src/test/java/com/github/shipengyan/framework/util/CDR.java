package com.github.shipengyan.framework.util;


import com.github.shipengyan.framework.domain.BaseDomain;
import com.github.shipengyan.framework.util.excel.ExcelCell;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-07 11:13
 * @since 1.0
 */
@Slf4j
@Data
@Accessors(chain = true)
public class CDR extends BaseDomain {

    @ExcelCell(index = 0)
    private String caller;

    @ExcelCell(index = 1)
    private String calledParty;

    @ExcelCell(index = 2)
    private Date beginDate;

    @ExcelCell(index = 3, format = "yyyy/MM/dd")
    private Date endDate;
}
