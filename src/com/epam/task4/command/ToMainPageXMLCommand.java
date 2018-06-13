package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToMainPageXMLCommand extends XMLCommand {

    public ToMainPageXMLCommand(Handler handler) {
        super(handler);
    }

    public ToMainPageXMLCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        getHandler().handle(XMLCommandType.TO_MAIN_PAGE_COMMAND,request,response,servletContext);
    }
}
