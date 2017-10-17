package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class StringUtil {


    /**
     * 按空格分割
     *
     * @param str
     * @return
     */
    public static String[] splitBySpace(String str) {
        return str.split("\\s+");
    }


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

    /**
     * 按双字节截取字符串 </br>
     * 中文算两个字符
     *
     * @param str
     * @param length
     * @return
     */
    public static String subStr(String str, int length) {
        int size = length(str);
        if (size <= length) {
            return str;
        }


        byte[] bytes;
        try {
            bytes = str.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            int i = 2; // 要截取的字节数，从第3个字节开始
            for (; i < bytes.length && n < length; i++) {
                // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                if (i % 2 == 1) {
                    n++; // 在UCS2第二个字节时n加1
                } else {
                    // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    if (bytes[i] != 0) {
                        n++;
                    }
                }
            }
            // 如果i为奇数时，处理成偶数
            if (i % 2 == 1) {
                // 该UCS2字符是汉字时，去掉这个截一半的汉字
                if (bytes[i - 1] != 0)
                    i = i - 1;
                    // 该UCS2字符是字母或数字，则保留该字符
                else
                    i = i + 1;
            }

            return new String(bytes, 0, i, "Unicode");
        } catch (UnsupportedEncodingException e) {
            log.error("fail to encoding", e);
        }
        return "";
    }

    /**
     * 统一用双字节长度
     *
     * @param str
     * @return
     */
    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        str = str.replaceAll("[^\\x00-\\xff]", "**");
        return str.length();
    }
}
