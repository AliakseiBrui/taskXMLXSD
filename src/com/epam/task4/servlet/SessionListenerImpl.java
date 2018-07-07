package com.epam.task4.servlet;

import com.epam.task4.config.LocaleConfigurator;
import com.epam.task4.constant.LocaleConstant;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerImpl implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LocaleConfigurator.INSTANCE.configureLocale(LocaleConstant.RUS,httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
