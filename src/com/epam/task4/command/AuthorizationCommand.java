package com.epam.task4.command;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Router;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class AuthorizationCommand extends XmlCommand {

    public AuthorizationCommand(CommandService service) {
        super(service);
    }

    @Override
    public Router execute(HttpServletRequest request)
            throws ServletException, IOException {
        HashMap<String, String> parameterMap = new HashMap<>();
        HashMap<String, Object> attributeMap = new HashMap<>();
        parameterMap.put(ParameterConstant.LOGIN_PARAMETER, request.getParameter(ParameterConstant.LOGIN_PARAMETER));
        parameterMap.put(ParameterConstant.PASSWORD_PARAMETER,request.getParameter(ParameterConstant.PASSWORD_PARAMETER));

        getService().process(parameterMap,attributeMap);

        if((boolean) attributeMap.get(AttributeConstant.LOGGED_IN_ATTRIBUTE)){
            request.getSession().setAttribute(AttributeConstant.LOGIN_ATTRIBUTE,parameterMap.get(ParameterConstant.LOGIN_PARAMETER));
        }
        request.setAttribute(AttributeConstant.ERROR_MESSAGE_ATTRIBUTE,attributeMap.get(AttributeConstant.ERROR_MESSAGE_ATTRIBUTE));
        return (Router) attributeMap.get(AttributeConstant.ROUTER_ATTRIBUTE);
    }
}
