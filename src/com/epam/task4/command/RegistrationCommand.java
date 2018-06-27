package com.epam.task4.command;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Answer;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegistrationCommand extends XMLCommand {

    public RegistrationCommand(CommandService service) {
        super(service);
    }

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        HashMap<String, String> parameterMap = new HashMap<>();
        HashMap<String, Object> attributeMap = new HashMap<>();

        parameterMap.put(ParameterConstant.LOGIN_PARAMETER, request.getParameter(ParameterConstant.LOGIN_PARAMETER));
        parameterMap.put(ParameterConstant.PASSWORD_PARAMETER, request.getParameter(ParameterConstant.PASSWORD_PARAMETER));

        getService().process(parameterMap,attributeMap);

        request.setAttribute(AttributeConstant.MESSAGE_ATTRIBUTE, attributeMap.get(AttributeConstant.MESSAGE_ATTRIBUTE));
        request.setAttribute(AttributeConstant.ERROR_MESSAGE_ATTRIBUTE, attributeMap.get(AttributeConstant.ERROR_MESSAGE_ATTRIBUTE));

        return (Answer) attributeMap.get(AttributeConstant.ANSWER_ATTRIBUTE);
    }
}
