package com.github.shipengyan.framework.util;

import com.github.shipengyan.framework.util.excel.ExcelLogs;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.util.*;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-07 11:05
 * @since 1.0
 */
@Slf4j
public class ExcelUtilTest {

    public static final String PATH_FILE_2003 = "c:/test2.xls";
    public static final String PATH_FILE_2007 = "c:/test2.xlsx";


    @Test
    public void exportListTest() throws Exception {

        Map<String, String> header = new LinkedHashMap<>();
        header.put("caller", "呼叫方");
        header.put("calledParty", "被呼叫方");
        header.put("beginDate", "开始时间");
        header.put("endDate", "结束时间");


        List<CDR> data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            CDR cdr = new CDR();
            cdr.setCaller("188000000" + i)
               .setCalledParty("158000000" + i)
               .setBeginDate(new Date())
               .setEndDate(new Date());
            data.add(cdr);
        }


        ExcelUtil.exportExcel(PATH_FILE_2003, header, data);
        ExcelUtil.exportExcel(PATH_FILE_2007, header, data);
    }

    @Test
    public void readExcelTest() throws Exception {
        readExcel(PATH_FILE_2003);
        readExcel(PATH_FILE_2007);
    }

    private void readExcel(final String path) {
        log.debug("file-->{}", path);
        List<CDR> data = (List<CDR>) ExcelUtil.readExcel(CDR.class, path, new ExcelLogs());

        for (int i = 0; i < data.size(); i++) {
            CDR cdr = data.get(i);
            log.debug(cdr.toString());
        }
    }


    @Test
    public void exportMapTest() throws Exception {

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object>       map  = new LinkedHashMap<>();
        map.put("name", "");
        map.put("age", "");
        map.put("birthday", "");
        map.put("sex", "");

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("name", null);
        map2.put("age", null);
        map2.put("sex", null);
        map.put("birthday", null);

        Map<String, Object> map3 = new LinkedHashMap<>();
        map3.put("name", "张三");
        map3.put("age", 12);
        map3.put("sex", "男");
        map3.put("birthday", new Date());
        list.add(map);
        list.add(map2);
        list.add(map3);

        Map<String, String> header = new LinkedHashMap<>();
        header.put("name", "姓名");
        header.put("age", "年龄");
        header.put("birthday", "出生日期");
        header.put("sex", "性别");

        ExcelUtil.exportExcel("c:/test.xls", header, list);

    }

    @Test
    public void enumTest() {
        System.out.println(CellType.STRING.toString());
        System.out.println(CellType.STRING.ordinal());

    }

}

