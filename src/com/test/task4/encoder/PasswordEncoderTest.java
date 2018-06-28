package com.test.task4.encoder;

import com.epam.task4.encoder.PasswordEncoder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordEncoderTest {
    private PasswordEncoder passwordEncoder = new PasswordEncoder();
    private static final String PASSWORD = "1234";

    @Test
    public void testEncryptPassword1(){
        Assert.assertNotNull(passwordEncoder.encryptPassword(PASSWORD));
    }

    @Test
    public void testEncryptPassword2(){
        int expected = 64;
        Assert.assertEquals(expected,passwordEncoder
                .encryptPassword(PASSWORD).length());
    }
}
