package com.github.shipengyan.framework.util.excel;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-07 11:03
 * @since 1.0
 */
public class ExcelLog {
    private Integer rowNum;
    private Object  object;
    private String  log;

    /**
     * @return the rowNum
     */
    public Integer getRowNum() {
        return rowNum;
    }

    /**
     * @param rowNum the rowNum to set
     */
    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * @param object
     * @param log
     */
    public ExcelLog(Object object, String log) {
        super();
        this.object = object;
        this.log = log;
    }

    /**
     * @param rowNum
     * @param object
     * @param log
     */
    public ExcelLog(Object object, String log, Integer rowNum) {
        super();
        this.rowNum = rowNum;
        this.object = object;
        this.log = log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(String log) {
        this.log = log;
    }
}
