package com.epam.task4.command;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.entity.Router;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class ToAnyPageCommand extends XmlCommand {

    public ToAnyPageCommand(CommandService service) {
        super(service);
    }

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        HashMap<String, Object> attributeMap = new HashMap<>();

        getService().process(null,attributeMap);

        return (Router) attributeMap.get(AttributeConstant.ROUTER_ATTRIBUTE);
    }
}
