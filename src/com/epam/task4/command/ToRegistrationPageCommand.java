package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationPageCommand extends XMLCommand{
    public ToRegistrationPageCommand(Handler handler) {
        super(handler);
    }

    public ToRegistrationPageCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        getHandler().handle(XMLCommandType.TO_REGISTRATION_PAGE_COMMAND,request,response,servletContext);
    }
}
