package com.epam.task4.service;

import com.epam.task4.config.LocaleConfigurator;
import com.epam.task4.constant.*;
import com.epam.task4.entity.Router;
import com.epam.task4.factory.RouterFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ChangeLocaleService implements CommandService{
    @Override
    public void process(Map<String, String> parameterMap, Map<String, Object> attributeMap) throws ServletException, IOException {
        Language language = Language.valueOf(parameterMap.get(ParameterConstant.LANG_PARAMETER));
        HttpSession session = (HttpSession) attributeMap.get(AttributeConstant.SESSION_ATTRIBUTE);
        String path = parameterMap.get(ParameterConstant.PAGE_PATH_PARAMETER);

        LocaleConfigurator.INSTANCE.configureLocale(language.getLocale(),session);

        attributeMap.put(AttributeConstant.ROUTER_ATTRIBUTE,RouterFactory.createRouter(Router.RouteType.REDIRECT,path));
    }
}
