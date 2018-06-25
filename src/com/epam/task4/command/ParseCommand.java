package com.epam.task4.command;

import com.epam.task4.entity.Answer;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ParseCommand extends XMLCommand {
    private static final String XML_FILE_PATH = "someDevices.xml";

    public ParseCommand(CommandService service) {
        super(service);
    }

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        HashMap<String, String> parameterMap = new HashMap<>();
        HashMap<String, Object> attributeMap = new HashMap<>();
        parameterMap.put("parserType", request.getParameter("parserType"));
        parameterMap.put("pathToXML", servletContext.getRealPath("/res/"+XML_FILE_PATH));

        getService().process(parameterMap,attributeMap);

        request.setAttribute("pcComponentSet", attributeMap.get("pcComponentSet"));
        request.setAttribute("phoneSet", attributeMap.get("phoneSet"));
        request.setAttribute("parserType", attributeMap.get("parserType"));
        return (Answer) attributeMap.get(CommandService.ANSWER_ATTRIBUTE);
    }
}
