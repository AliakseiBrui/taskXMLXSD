package com.epam.task4.command;

import com.epam.task4.entity.Answer;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LogOutCommand extends XMLCommand {
    public LogOutCommand(CommandService service) {
        super(service);
    }

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        HashMap<String, Object> attributeMap = new HashMap<>();

        getService().process(null,attributeMap);

        request.getSession().removeAttribute("login");
        return (Answer) attributeMap.get(CommandService.ANSWER_ATTRIBUTE);
    }
}
