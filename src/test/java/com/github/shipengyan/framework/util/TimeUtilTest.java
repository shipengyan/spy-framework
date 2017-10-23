package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-10-23 10:35
 * @since 1.0
 */
@Slf4j
public class TimeUtilTest {

    @Test
    public void run16() throws Exception {
        Date now = new Date();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date beginDate = format.parse("8:00"); // 1970
        Date endDate   = format.parse("17:00");

        Calendar calendar = Calendar.getInstance();// 2017
        calendar.set(Calendar.HOUR_OF_DAY, 24);

        int hour   = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Date now2 = calendar.getTime();


        log.debug("begin={},end={}", beginDate, endDate);
        log.debug("hour={},minute={}", hour, minute);

        log.debug("now2={}", now2);

        Date date1 = format.parse("8:00");

        log.debug("compare[beginDate,date1], result={}", beginDate.before(date1));
    }

    @Test
    public void run49() throws Exception {
        log.debug("{}", TimeUtil.nowIsInRange("11:20:00", "13:00:00", TimeUtil.FORMAT_HHmmSS));
        log.debug("{}", TimeUtil.nowIsInRange("8:00", "12:00", TimeUtil.FORMAT_HHmm));
    }


}
