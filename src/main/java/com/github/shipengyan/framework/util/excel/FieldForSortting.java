package com.github.shipengyan.framework.util.excel;

import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-07 10:59
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class FieldForSortting {
    private Field field;
    private int   index;

    /**
     * @param field
     */
    public FieldForSortting(Field field) {
        super();
        this.field = field;
    }

    /**
     * @param field
     * @param index
     */
    public FieldForSortting(Field field, int index) {
        super();
        this.field = field;
        this.index = index;
    }

}
