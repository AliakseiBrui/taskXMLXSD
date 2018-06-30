package com.epam.task4.service;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.PagePath;
import com.epam.task4.entity.Router;
import com.epam.task4.factory.RouterFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public class ToAuthorizationPageService implements CommandService {
    @Override
    public void process(Map<String, String> parameterMap, Map<String, Object> attributeMap)
            throws ServletException, IOException {

        attributeMap.put(AttributeConstant.ROUTER_ATTRIBUTE,RouterFactory
                .createRouter(Router.RouteType.REDIRECT,PagePath.AUTHORIZATION_PAGE));
    }
}
