package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface XMLCommand {
    void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException;
}