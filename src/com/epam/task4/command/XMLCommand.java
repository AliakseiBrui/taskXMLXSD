package com.epam.task4.command;

import com.epam.task4.commandhandler.CommandHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XMLCommand {
    private CommandHandler handler;
    private static final Logger LOGGER = LogManager.getLogger(XMLCommand.class);

    XMLCommand(CommandHandler handler) {
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
        LOGGER.debug("Executing command.");

        if(handler!=null) {
            handler.handle(request, response, servletContext);
        }
    }
}
