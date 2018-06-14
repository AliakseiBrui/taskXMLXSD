package com.epam.task4.command;

import com.epam.task4.commandhandler.CommandHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XMLCommand {
    private CommandHandler handler;

    public XMLCommand(CommandHandler handler) {
        this.handler = handler;
    }

    public XMLCommand() {
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public void setHandler(CommandHandler handler) {
        this.handler = handler;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException{

        if(handler!=null) {
            handler.handle(request, response, servletContext);
        }
    }
}
