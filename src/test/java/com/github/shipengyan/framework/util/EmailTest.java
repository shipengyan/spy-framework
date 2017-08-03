package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-03 9:55
 * @since 1.0
 */
@Slf4j
public class EmailTest {

    /**
     * 测试前注入如下内容
     * 1. smtp
     * 2. auth username
     * 3. auth pwd
     *
     * @throws Exception
     */
    @Test
    public void sendEmail() throws Exception {

        Email email = new EmailBuilder()
            .from("lollypop", "spy19881221@163.com")  //163 要求发件人必须和auth user保持一致
            .to("C. Cane", "spy19881221@163.com")
            .subject("hey")
            .text("We should meet up! ;)")
            .build();

        String smtpHost     = "smtp.163.com";
        String authUserName = "spy19881221";
        String authPwd      = ""; //TODO


        Mailer mailer = new Mailer(smtpHost, 25, authUserName, authPwd);
        mailer.sendMail(email);
    }
}
