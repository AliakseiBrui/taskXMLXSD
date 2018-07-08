package com.epam.task4.command;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.entity.Router;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class LogOutCommand extends XmlCommand {

    public LogOutCommand(CommandService service) {
        super(service);
    }

    @Override
    public Router execute(HttpServletRequest request)
            throws ServletException, IOException {
        HashMap<String, Object> attributeMap = new HashMap<>();

        getService().process(null,attributeMap);

        request.getSession().removeAttribute(AttributeConstant.LOGIN_ATTRIBUTE);
        return (Router) attributeMap.get(AttributeConstant.ROUTER_ATTRIBUTE);
    }
}
