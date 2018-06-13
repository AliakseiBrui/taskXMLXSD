package com.test.task4.encoder;

import com.epam.task4.encoder.PasswordEncoder;
import org.testng.annotations.Test;

public class PasswordEncoderTest {
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Test
    public void testEncryptPassword(){

        String encryptedPassword = passwordEncoder.encryptPassword("123");
        System.out.println(encryptedPassword);
        System.out.println(encryptedPassword.length());
    }
}
