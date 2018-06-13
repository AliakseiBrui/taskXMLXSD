package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends XMLCommand {
    public RegistrationCommand(Handler handler) {
        super(handler);
    }

    public RegistrationCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        getHandler().handle(XMLCommandType.REGISTRATION_COMMAND,request,response,servletContext);
    }
}
