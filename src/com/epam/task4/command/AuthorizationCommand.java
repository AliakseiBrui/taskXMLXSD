package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationCommand extends XMLCommand {
    public AuthorizationCommand(Handler handler) {
        super(handler);
    }

    public AuthorizationCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        getHandler().handle(XMLCommandType.AUTHORIZATION_COMMAND,request,response,servletContext);
    }
}
