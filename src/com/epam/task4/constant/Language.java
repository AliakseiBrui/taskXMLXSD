package com.epam.task4.constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Language {
    RUS("ru_RU"),
    ENG("en_US");

    private String locale;
    private static final Logger LOGGER = LogManager.getLogger(Language.class);

    Language(String locale){
        this.locale = locale;
    }

    public String getLocale() {
        LOGGER.debug("Getting locale "+locale+".");
        return locale;
    }
}
