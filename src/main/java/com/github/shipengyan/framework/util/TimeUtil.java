package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-10-23 10:32
 * @since 1.0
 */
@Slf4j
public final class TimeUtil {

    public static final String FORMAT_HHmmSS = "HH:mm:ss";
    public static final String FORMAT_HHmm   = "HH:mm";

    /**
     * 判断当前时间是否在指定范围内，注意指定时间格式
     *
     * @param beginTime
     * @param endTime
     * @param timeFormatStr
     * @return
     */
    public static boolean nowIsInRange(String beginTime, String endTime, String timeFormatStr) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormatStr);

        try {
            Date beginDate = format.parse(beginTime); // 1970
            Date endDate   = format.parse(endTime);

            Calendar calendar = Calendar.getInstance();// 2017
            calendar.set(Calendar.HOUR_OF_DAY, 24);
            calendar.setTime(new Date());

            int hour   = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            Date now;
            if (EqualsUtil.equals(timeFormatStr, FORMAT_HHmmSS)) {
                now = format.parse(hour + ":" + minute + ":" + second);
            } else {
                now = format.parse(hour + ":" + minute);
            }

            return now.before(endDate) && now.after(beginDate);
        } catch (Exception e) {
            log.error("fail to parse time", e);
        }

        return false;
    }


}
