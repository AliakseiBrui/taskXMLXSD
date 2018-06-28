package com.epam.task4.command;

import com.epam.task4.entity.Router;
import com.epam.task4.service.CommandService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

    public abstract Router execute(HttpServletRequest request)
            throws ServletException, IOException;
}
