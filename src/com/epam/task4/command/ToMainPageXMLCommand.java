package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToMainPageXMLCommand implements XMLCommand {
    private Handler handler;

    public ToMainPageXMLCommand() {
    }

    public ToMainPageXMLCommand(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        handler.handle(XMLCommandType.TO_MAIN_PAGE_COMMAND,request,response,servletContext);
    }
}
