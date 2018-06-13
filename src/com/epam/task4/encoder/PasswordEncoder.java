package com.epam.task4.encoder;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {

    public String encryptPassword(String password){

        return DigestUtils.sha256Hex(password);
    }
}
