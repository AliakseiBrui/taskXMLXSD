package com.epam.task4.command;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Router;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class ParseCommand extends XMLCommand {
    private static final String XML_FILE_PATH = "someDevices.xml";

    public ParseCommand(CommandService service) {
        super(service);
    }

    @Override
    public Router execute(HttpServletRequest request, ServletContext servletContext)
            throws ServletException, IOException {
        HashMap<String, String> parameterMap = new HashMap<>();
        HashMap<String, Object> attributeMap = new HashMap<>();
        parameterMap.put(ParameterConstant.PARSER_TYPE_PARAMETER, request.getParameter(ParameterConstant.PARSER_TYPE_PARAMETER));
        parameterMap.put(ParameterConstant.PATH_TO_XML_PARAMETER, servletContext.getRealPath("/res/"+XML_FILE_PATH));

        getService().process(parameterMap,attributeMap);

        request.setAttribute(AttributeConstant.PC_COMPONENT_SET_ATTRIBUTE, attributeMap.get(AttributeConstant.PC_COMPONENT_SET_ATTRIBUTE));
        request.setAttribute(AttributeConstant.PHONE_SET_ATTRIBUTE, attributeMap.get(AttributeConstant.PHONE_SET_ATTRIBUTE));
        request.setAttribute(AttributeConstant.PARSER_TYPE_ATTRIBUTE, attributeMap.get(AttributeConstant.PARSER_TYPE_ATTRIBUTE));
        return (Router) attributeMap.get(AttributeConstant.ROUTER_ATTRIBUTE);
    }
}
