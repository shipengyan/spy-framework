package com.github.shipengyan.framework.util.encrypt;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * MD5
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-08 11:08
 * @since 1.0
 */
@Slf4j
public class MD5 {

    /**
     * 对字符串进行MD5加密
     *
     * @param s
     * @return
     * @author tianwl
     */
    public final static String getStringMD5(String s) {
        byte[] strTemp = s.getBytes();
        return MD5.getByteArrayMD5(strTemp);
    }

    /**
     * 对byte数组进行MD5加密
     *
     * @param source
     * @return
     * @author tianwl
     */
    public final static String getByteArrayMD5(byte[] source) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(source);
            byte[] md    = mdTemp.digest();
            int    j     = md.length;
            char   str[] = new char[j * 2];
            int    k     = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                // 将每个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("Exception", e);
            return null;
        }
    }

}

