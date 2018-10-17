package com.github.shipengyan.framework.util;

import com.github.shipengyan.framework.util.sort.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/10/17
 * @since 1.0
 */
@Slf4j
public class SortUtilTest {

    private int[] data = {6, 4, 3, 9, 10};

    @Test
    public void run17() throws Exception {
        SortUtil.sort(data, SortUtil.BUBBLE);
    }

    @Test
    public void run26() throws Exception {
        SortUtil.sort(data, SortUtil.IMPROVED_QUICK);
    }


    @Test
    public void run32() throws Exception {
        SortUtil.sort(data, SortUtil.HEAP);
    }

    @Test
    public void run37() throws Exception {
        SortUtil.sort(data, SortUtil.IMPROVED_MERGE);
    }

    @Test
    public void run42() throws Exception {
        SortUtil.sort(data, SortUtil.INSERT);
    }


    @Test
    public void run48() throws Exception {
        SortUtil.sort(data, SortUtil.MERGE);
    }

    @Test
    public void run53() throws Exception {
        SortUtil.sort(data, SortUtil.QUICK);
    }

    @Test
    public void run58() throws Exception {
        SortUtil.sort(data, SortUtil.SELECTION);
    }

    @Test
    public void run63() throws Exception {
        SortUtil.sort(data, SortUtil.SHELL);
    }

    @After
    public void after() {
        log.info("{}", data);
    }
}
