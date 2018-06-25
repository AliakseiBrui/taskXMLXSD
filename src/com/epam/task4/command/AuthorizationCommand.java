package com.epam.task4.command;

import com.epam.task4.entity.Answer;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AuthorizationCommand extends XMLCommand {
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";

    public AuthorizationCommand(CommandService service) {
        super(service);
    }

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        HashMap<String, String> parameterMap = new HashMap<>();
        HashMap<String, Object> attributeMap = new HashMap<>();
        parameterMap.put(LOGIN_PARAMETER, request.getParameter(LOGIN_PARAMETER));
        parameterMap.put(PASSWORD_PARAMETER,request.getParameter(PASSWORD_PARAMETER));

        getService().process(parameterMap,attributeMap);

        if((boolean) attributeMap.get("logged in")){
            request.getSession().setAttribute(LOGIN_PARAMETER,parameterMap.get(LOGIN_PARAMETER));
        }
        request.setAttribute("errorMessage",attributeMap.get("errorMessage"));
        return (Answer) attributeMap.get(CommandService.ANSWER_ATTRIBUTE);
    }
}
