package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class XMLCommand {
    private Handler handler;

    XMLCommand(Handler handler) {
        this.handler = handler;
    }

    XMLCommand() {
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException;
}
