package com.epam.task4.command;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Router;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class ChangeLocaleCommand extends XMLCommand {
    public ChangeLocaleCommand(CommandService service) {
        super(service);
    }

    public ChangeLocaleCommand() {
    }

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        HashMap<String, String> parameterMap = new HashMap<>();
        HashMap<String, Object> attributeMap = new HashMap<>();

        parameterMap.put(ParameterConstant.LANG_PARAMETER,request.getParameter(ParameterConstant.LANG_PARAMETER));
        parameterMap.put(ParameterConstant.PAGE_PATH_PARAMETER,request.getParameter(ParameterConstant.PAGE_PATH_PARAMETER));
        attributeMap.put(AttributeConstant.SESSION_ATTRIBUTE,request.getSession());

        getService().process(parameterMap,attributeMap);

        return (Router) attributeMap.get(AttributeConstant.ROUTER_ATTRIBUTE);
    }
}
