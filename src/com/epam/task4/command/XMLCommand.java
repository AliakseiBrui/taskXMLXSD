package com.epam.task4.command;

import com.epam.task4.entity.Answer;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class XMLCommand {
    private CommandService service;


    XMLCommand(CommandService service) {
        this.service = service;
    }

    XMLCommand() {
    }

    public CommandService getService() {
        return service;
    }

    public void setService(CommandService service) {
        this.service = service;
    }

    public abstract Answer execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException;
}
