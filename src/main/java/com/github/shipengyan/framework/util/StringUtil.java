package com.github.shipengyan.framework.util;

import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(String str) {
        if (str != null && str.length() != 0) {
            return true;
        }
        return false;
    }

    public static String blankIfNull(String target) {
        if (target == null) {
            return "";
        } else {
            return target;
        }
    }

    /**
     * 将对象转换成String
     *
     * @param val Object
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String toString(Object val) {
        if (val == null) {
            return "";
        }
        if (val.getClass().isArray()) {
            return arrayToString((Object[]) val);
        }

        if (val instanceof List) {
            return listToString((List) val, null);
        }
        return val.toString();
    }

    /**
     * 将对象数组转换成String
     *
     * @param objs Object[]
     * @return String
     */
    public static String arrayToString(Object[] objs) {
        if (objs == null) {
            return "";
        } else {
            int           size = objs.length;
            StringBuilder buff = new StringBuilder();
            for (int i = 0; i < size; i++) {
                buff.append(objs[i].toString()).append("\n");
            }
            return buff.toString();
        }
    }

    /**
     * 将List列表转换成字符串,取得列表里的每个对象调用其toString()方法
     *
     * @param list     List
     * @param itemName String 列表条目名称
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String listToString(List list, String itemName) {
        if (list == null) {
            return "";
        } else {
            int           size = list.size();
            StringBuilder buff = new StringBuilder();
            for (int i = 0; i < size; i++) {
                buff.append(list.get(i).toString()).append("\n");
            }
            return buff.toString();
        }
    }
}
