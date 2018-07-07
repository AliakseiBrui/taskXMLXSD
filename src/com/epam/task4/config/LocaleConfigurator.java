package com.epam.task4.config;

import com.epam.task4.constant.AttributeConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

public enum LocaleConfigurator {
    INSTANCE;
    private static final Logger LOGGER = LogManager.getLogger(LocaleConfigurator.class);

    public void configureLocale (String locale, HttpSession session){
        LOGGER.debug("Configuring locale "+locale+".");
        session.setAttribute(AttributeConstant.LOCALE_ATTRIBUTE,locale);
    }
}
