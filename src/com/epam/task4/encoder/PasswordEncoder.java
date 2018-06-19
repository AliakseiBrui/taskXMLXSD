package com.epam.task4.encoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordEncoder {
    private static final Logger LOGGER = LogManager.getLogger(PasswordEncoder.class);

    public String encryptPassword(String password){
        LOGGER.debug("Encrypting password.");
        return DigestUtils.sha256Hex(password);
    }
}
