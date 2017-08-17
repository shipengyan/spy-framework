package com.github.shipengyan.framework.util.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Before;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-17 11:18
 * @since 1.0
 */
@Slf4j
public class EmailValidatorTest {
    private EmailValidator emailValidator;

    @Before

    public void setUp() {
        emailValidator = EmailValidator.getInstance();
    }


    @Test
    public void run16() throws Exception {
        validate("spy@cc");
        validate("spy@cc.com");
    }

    private void validate(String email) {

        log.debug("[{}] valid={}", email, emailValidator.isValid(email));
    }


}
