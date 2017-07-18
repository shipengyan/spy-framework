package com.github.shipengyan.framework.util;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import lombok.extern.slf4j.Slf4j;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;

/**
 * 检测文件类型
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 13:51
 * @since 1.0
 */
@Slf4j
public class MimeUtil {

    private MimeUtil() {

    }

    public static String getContentType(final String filePath) throws IOException {
        ContentInfoUtil util = new ContentInfoUtil();
        ContentInfo     info = util.findMatch(filePath);
        if (info == null) {
            //fallback to jdk
            return new MimetypesFileTypeMap().getContentType(filePath);

        } else {
            return info.getMimeType();
        }
    }

}
