package com.epam.task4.service;

import com.epam.task4.config.LocaleConfigurator;
import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.LocaleConstant;
import com.epam.task4.constant.PagePath;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Router;
import com.epam.task4.factory.RouterFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ChangeLocaleService implements CommandService{
    private static final String LANG_RUS = "RUS";
    private static final String LANG_ENG = "ENG";
    @Override
    public void process(Map<String, String> parameterMap, Map<String, Object> attributeMap) throws ServletException, IOException {
        String lang = parameterMap.get(ParameterConstant.LANG_PARAMETER);
        HttpSession session = (HttpSession) attributeMap.get(AttributeConstant.SESSION_ATTRIBUTE);
        String locale = null;

        switch (lang){
            case LANG_RUS:
                locale = LocaleConstant.RUS;
                break;
            case LANG_ENG:
                locale = LocaleConstant.ENG;
                break;
                default:
        }
        LocaleConfigurator.INSTANCE.configureLocale(locale,session);

        attributeMap.put(AttributeConstant.ROUTER_ATTRIBUTE,RouterFactory.createRouter(Router.RouteType.REDIRECT,PagePath.MAIN_PAGE));
    }
}
